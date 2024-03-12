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
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

    private String errorCode = "";

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String exception, String errorCode) {
        super(exception);
        this.errorCode = errorCode;
    }

}
