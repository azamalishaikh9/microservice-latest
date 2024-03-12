package com.ali.swipeservice.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Azam
 */
@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApiException extends RuntimeException{

    private String errorCode = "";

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String exception, String errorCode) {
        super(exception);
        this.errorCode = errorCode;
    }
}
