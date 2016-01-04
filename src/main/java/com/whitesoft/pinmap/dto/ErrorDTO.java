package com.whitesoft.pinmap.dto;

/**
 * Created by borisbondarenko on 04.01.16.
 *
 * A DTO to transfer an exception info
 *
 * @author brzzbr
 */
public class ErrorDTO {

    private String exceptionClass;

    private String stackTrace;

    private String errorMessage;

    public String getExceptionClass() {
        return exceptionClass;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ErrorDTO(String exceptionClass, String stackTrace, String errorMessage) {
        this.exceptionClass = exceptionClass;
        this.stackTrace = stackTrace;
        this.errorMessage = errorMessage;
    }
}
