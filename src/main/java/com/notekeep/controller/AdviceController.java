package com.notekeep.controller;

import com.notekeep.exception.label.LabelAlreadyExistsException;
import com.notekeep.exception.user.UserAlreadyExistsException;
import com.notekeep.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class AdviceController extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        String message = result.getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .map(value -> messageSource.getMessage(value, null, request.getLocale()))
                .collect(Collectors.joining("\n"));
        return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {UserNotFoundException.class, UserAlreadyExistsException.class})
    public ResponseEntity<?> handleAuthException(RuntimeException ex, WebRequest webRequest){
        String message = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
        Map<String, String> body = Map.of("message", message);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(value = LabelAlreadyExistsException.class)
    public ResponseEntity<?> handleLabelAlreadyExistsException(LabelAlreadyExistsException ex, WebRequest webRequest) {
        String message = messageSource.getMessage("label.already.exists", new Object[]{ex.getMessage()}, LocaleContextHolder.getLocale());
        return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }
}
