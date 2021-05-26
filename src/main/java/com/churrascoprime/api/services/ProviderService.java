package com.churrascoprime.api.services;

import com.churrascoprime.api.constants.FileConstant;
import com.churrascoprime.api.exceptions.NotAnImageFileException;
import com.churrascoprime.api.exceptions.RecordNotFoundException;
import com.churrascoprime.api.models.ProviderModel;
import com.churrascoprime.api.repositories.ProviderRepository;
import com.churrascoprime.api.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;
    private static final String NOT_FOUND = "provider.notFound";

    @Autowired
    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public ProviderModel findById(Long idProvider) {
        return providerRepository.findById(idProvider).orElseThrow(() -> new RecordNotFoundException(NOT_FOUND));
    }

    public Page<ProviderModel> findAll(String filter, Pageable pageable) {
        if (StringUtils.isValid(filter)) {
            return providerRepository.findAllByDateDeletedIsNullAndNameContainingIgnoreCase(filter, pageable);
        }
        return providerRepository.findAllByDateDeletedIsNull(pageable);
    }

    public ProviderModel save(ProviderModel provider, MultipartFile imgFile) throws IOException, NotAnImageFileException {
        saveImage(provider, imgFile);
        return providerRepository.save(provider);
    }

    public ProviderModel update(ProviderModel updatedProvider) {
        ProviderModel provider = findById(updatedProvider.getIdProvider());
        provider.update(updatedProvider);
        return provider;
    }

    public void delete(Long idProvider) {
        ProviderModel provider = findById(idProvider);
        provider.setDateDeleted(new Date());
        providerRepository.save(provider);
    }

    public List<ProviderModel> findTop3ActiveProviders() {
        return providerRepository.findTop3ByActiveIsTrueAndDateDeletedIsNullOrderByRatingDesc();
    }

    private void saveImage(ProviderModel provider, MultipartFile imgFile) throws NotAnImageFileException, IOException {
        if (!Arrays.asList(MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE)
                .contains(imgFile.getContentType())) {
            throw new NotAnImageFileException(imgFile.getOriginalFilename() + " is not an image file. " +
                    "Please upload an image");
        }
        String folder = FileConstant.USER_FOLDER;
        byte[] bytes = imgFile.getBytes();
        Path path = Paths.get(folder + provider.getName() + FileConstant.DOT
                + FileConstant.JPG_EXTENSION);
        Files.write(path, bytes);
        provider.setImageUrl(path.toString());
    }
}
