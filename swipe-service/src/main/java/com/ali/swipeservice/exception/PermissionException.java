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
@ResponseStatus(HttpStatus.FORBIDDEN)
public class PermissionException extends RuntimeException {

    private String errorCode = "";

    public PermissionException(String message) {
        super(message);
    }

    public PermissionException(String exception, String errorCode){
        super(exception);
        this.errorCode = errorCode;
    }
}
