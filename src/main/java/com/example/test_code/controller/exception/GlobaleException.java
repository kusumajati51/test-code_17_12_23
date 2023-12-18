package com.example.test_code.controller.exception;

import java.io.IOException;
import java.util.*;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.test_code.dto.response.BaseResponse;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobaleException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> validationViolationException(HttpServletResponse response, ValidationException e) throws IOException {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        baseResponse.setStatus(false);
        baseResponse.setMessage(e.getMessage());
        baseResponse.setData(e);
        ResponseEntity<Object> entity = new ResponseEntity<>(baseResponse,HttpStatus.BAD_REQUEST);
        return entity;
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        BaseResponse<Object> response = new BaseResponse<>();
        response.setStatus(false);
        response.setMessage(ex.getLocalizedMessage());
        Map<String, Object> subBody = new HashMap<>();

//        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
//        for (FieldError fieldError : fieldErrors) {
//            String field = fieldError.getField();
//            String errorMessages = fieldError.getDefaultMessage();
//            subBody.put(field, errorMessages);
//        }

        response.setData(subBody);

        return new ResponseEntity<>(response, headers, status);
    }
}
