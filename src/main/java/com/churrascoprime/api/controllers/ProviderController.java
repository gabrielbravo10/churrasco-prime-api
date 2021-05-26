package com.churrascoprime.api.controllers;

import com.churrascoprime.api.constants.FileConstant;
import com.churrascoprime.api.dtos.provider.ProviderDto;
import com.churrascoprime.api.dtos.provider.ProviderFormDto;
import com.churrascoprime.api.exceptions.NotAnImageFileException;
import com.churrascoprime.api.models.ProviderModel;
import com.churrascoprime.api.services.ProviderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(path = {"/", "/providers"})
public class ProviderController {

    private final ProviderService providerService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProviderController(ProviderService providerService, ModelMapper modelMapper) {
        this.providerService = providerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{idProvider}")
    public ResponseEntity<ProviderDto> show(@PathVariable Long idProvider) {
        ProviderDto provider = modelMapper.map(providerService.findById(idProvider), ProviderDto.class);
        return ResponseEntity.ok(provider);
    }

    @GetMapping
    public ResponseEntity<Page<ProviderDto>> index(
            @RequestParam(value = "filter", required = false) String filter,
            @PageableDefault(sort = {"active"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ProviderDto> providers = providerService
                .findAll(filter, pageable)
                .map(provider -> modelMapper.map(provider, ProviderDto.class));
        return ResponseEntity.ok(providers);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ProviderDto> store(
            @Valid @RequestBody ProviderFormDto providerFormDto,
            @RequestPart("imgFile") MultipartFile imgFile,
            UriComponentsBuilder uriComponentsBuilder) throws IOException, NotAnImageFileException {
        ProviderModel provider = modelMapper.map(providerFormDto, ProviderModel.class);
        ProviderDto newProvider = modelMapper.map(providerService.save(provider, imgFile), ProviderDto.class);
        URI uri = uriComponentsBuilder.path("/providers/{id}").buildAndExpand(newProvider.getIdProvider()).toUri();
        return ResponseEntity.created(uri).body(newProvider);
    }

    @Transactional
    @PutMapping("/{idProvider}")
    public ResponseEntity<ProviderDto> update(@PathVariable Long idProvider, @Valid @RequestBody ProviderFormDto providerFormDto) {
        ProviderModel provider = modelMapper.map(providerFormDto, ProviderModel.class);
        provider.setIdProvider(idProvider);
        ProviderDto updatedProvider = modelMapper.map(providerService.update(provider), ProviderDto.class);
        return ResponseEntity.ok().body(updatedProvider);
    }

    @Transactional
    @DeleteMapping("/{idProvider}")
    public ResponseEntity<?> delete(@PathVariable Long idProvider) {
        providerService.delete(idProvider);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/top3")
    public ResponseEntity<List<ProviderDto>> findTop3() {
        List<ProviderDto> providers = providerService.findTop3ActiveProviders()
                .stream()
                .map(provider -> modelMapper.map(provider, ProviderDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(providers);
    }

    @GetMapping(path = "/Users/gabrielbravo/Desktop/Testing/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImg( @PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(FileConstant.USER_FOLDER + FileConstant.FORWARD_SLASH + fileName));
    }
}
