package com.ali.swipeservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author Azam
 */

@ControllerAdvice
public class CustomizedException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllException(Exception exception, WebRequest webRequest) {

        if (exception instanceof ApiException apiException) {
            return getResponse(apiException.getErrorCode(), exception, webRequest, HttpStatus.BAD_REQUEST);
        }

        if (exception instanceof ServiceException serviceException) {
            return getResponse(serviceException.getErrorCode(), exception, webRequest, HttpStatus.BAD_REQUEST);
        }

        if (exception instanceof DataNotFoundException dataNotFoundException) {
            return getResponse(dataNotFoundException.getErrorCode(), exception, webRequest, HttpStatus.NOT_FOUND);
        }

        if (exception instanceof PermissionException permissionException) {
            return getResponse(permissionException.getErrorCode(), exception, webRequest, HttpStatus.FORBIDDEN);
        }

        if (exception instanceof UnauthorizedException unauthorizedException) {
            return getResponse(unauthorizedException.getErrorCode(), exception, webRequest, HttpStatus.FORBIDDEN);
        }

        return getResponse("NA", exception, webRequest, HttpStatus.BAD_REQUEST);
    }

    private static ResponseEntity<ExceptionResponse> getResponse(String errorCode, Exception exception, WebRequest webRequest, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ExceptionResponse(LocalDateTime.now(), errorCode, exception.getMessage(), getDescription(webRequest.getDescription(false))), httpStatus);
    }

    private static ResponseEntity<Object> getResponse(String errorCode, String errorMessage, WebRequest webRequest, HttpStatusCode statusCode) {
        return new ResponseEntity<>(new ExceptionResponse(LocalDateTime.now(), errorCode, errorMessage, getDescription(webRequest.getDescription(false))), statusCode);
    }

    private static String getDescription(String description) {
        if (description.startsWith("uri=")) {
            return description.substring(4);
        }
        return description;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest webRequest) {
        String errorCode = "Validation Error";
        HashMap<String, String> errorMessage = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorMessage.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return getResponse(errorCode, errorMessage.toString(), webRequest, status);
    }
}
