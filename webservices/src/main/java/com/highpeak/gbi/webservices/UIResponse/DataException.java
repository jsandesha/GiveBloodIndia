package com.highpeak.gbi.webservices.UIResponse;

import org.springframework.http.HttpStatus;

/**
 * @author sandesha, Created on 19/08/17
 */
public class DataException extends Exception {

    private String errorCode;

    private String errorMessage;

    private HttpStatus httpStatus;

    /**
     */
    public DataException(String errorCode, String errorMessage, HttpStatus httpStatus)
    {
        this.errorCode = errorCode;
        this.errorMessage= errorMessage;
        this.httpStatus=httpStatus;
    }

    /**
     * @return
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * @param httpStatus
     */
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
