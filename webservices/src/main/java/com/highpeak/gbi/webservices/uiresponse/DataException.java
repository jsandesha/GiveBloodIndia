package com.highpeak.gbi.webservices.uiresponse;

import org.springframework.http.HttpStatus;

/**
 * @author sandesha, Created on 19/08/17
 */
public class DataException extends Exception {

    private final String errorCode;

    private final String errorMessage;

    private final HttpStatus httpStatus;

    /**
     */
    public DataException(String errorCode, String errorMessage, HttpStatus httpStatus)
    {
        this.errorCode = errorCode;
        this.errorMessage= errorMessage;
        this.httpStatus=httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
