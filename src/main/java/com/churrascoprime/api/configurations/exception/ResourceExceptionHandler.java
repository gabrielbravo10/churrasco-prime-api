package com.churrascoprime.api.configurations.exception;


import com.churrascoprime.api.dtos.error.ErrorDto;
import com.churrascoprime.api.exceptions.NotAnImageFileException;
import com.churrascoprime.api.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ResourceExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public ResourceExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorDto> handle(MethodArgumentNotValidException exception) {
        List<ErrorDto> errors = new ArrayList<>();
        List<FieldError> validationErros = exception.getBindingResult().getFieldErrors();

        validationErros.forEach(error -> {
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            ErrorDto erroDTO = new ErrorDto(message, error.getField());
            errors.add(erroDTO);
        });
        return errors;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorDto handle(IllegalArgumentException exception) {
        return new ErrorDto(messageSource.getMessage(exception.getMessage(), null, LocaleContextHolder.getLocale()));
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public ErrorDto handle(RecordNotFoundException exception) {
        return new ErrorDto(messageSource.getMessage(exception.getMessage(), null, LocaleContextHolder.getLocale()));
    }

//    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
//    @ExceptionHandler(NegocioException.class)
//    public ErroDto handle(NegocioException exception) {
//        return new ErroDto(messageSource.getMessage(exception.getMessage(), null, LocaleContextHolder.getLocale()));
//    }

//    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(AuthenticationException.class)
//    public ErroDto handle(AuthenticationException exception) {
//        return new ErroDto(messageSource.getMessage("autenticacao.dadosInvalidos", null, LocaleContextHolder.getLocale()));
//    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotAnImageFileException.class)
    public ErrorDto handle(NotAnImageFileException exception) {
        return new ErrorDto(messageSource.getMessage(exception.getMessage(), null, LocaleContextHolder.getLocale()));
    }
}
