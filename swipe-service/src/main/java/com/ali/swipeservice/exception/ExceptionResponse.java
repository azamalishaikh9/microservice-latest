package com.ali.swipeservice.exception;

import java.time.LocalDateTime;

/**
 * @author Azam
 */
public record ExceptionResponse(LocalDateTime dateTime, String errorCode, String message, String details) {
}
