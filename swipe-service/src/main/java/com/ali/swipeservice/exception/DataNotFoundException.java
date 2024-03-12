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
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException{

    private String errorCode = "";

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String exception, String errorCode){
        super(exception);
        this.errorCode = errorCode;
    }
}
