package com.alc.gestock.handlers;

import com.alc.gestock.exception.EntityNotFoundException;
import com.alc.gestock.exception.ErrorCodes;
import com.alc.gestock.exception.InvalidEntityException;
import com.alc.gestock.exception.InvalidOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorDto  handleException(EntityNotFoundException exception, WebRequest webRequest){
        ErrorDto errorDto = new ErrorDto();
        final HttpStatus notFound = HttpStatus.NOT_FOUND;
        errorDto.setCode(exception.getErrorCodes());
        errorDto.setMessage(exception.getMessage());
        errorDto.setHttpCode(notFound.value());

        return errorDto;


    }

    @ExceptionHandler(InvalidEntityException.class)
    public ErrorDto handleException(InvalidEntityException exception , WebRequest webRequest){

        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(exception.getErrorCodes());
        errorDto.setMessage(exception.getMessage());
        errorDto.setHttpCode(badRequest.value());
        errorDto.setErrors(exception.getErrors());

        return errorDto;
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ErrorDto handleException(InvalidOperationException exception, WebRequest webRequest){

        final HttpStatus notFound = HttpStatus.BAD_REQUEST;
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(exception.getErrorCodes());
        errorDto.setMessage(exception.getMessage());
        errorDto.setHttpCode(notFound.value());

        return errorDto;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto> handleException(BadCredentialsException exception, WebRequest webRequest) {
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        final ErrorDto errorDto = ErrorDto.builder()
                .code(ErrorCodes.BAD_CREDENTIALS)
                .httpCode(badRequest.value())
                .message(exception.getMessage())
                .errors(Collections.singletonList("Login et / ou  mot de passe incorrecte"))
                .build();

        return new ResponseEntity<>(errorDto,   badRequest);
    }


}
