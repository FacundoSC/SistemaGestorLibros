package com.egg.web.library.exception;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ErrorInfo {

    @JsonProperty("message")
    private String message;
    @JsonProperty("status")
    private int statusCode;
    @JsonProperty("path")
    private String uriRequested;
    @JsonProperty("timestamp")
    private Date timestamp;


    public ErrorInfo(Exception exception, String uriRequested) {
        this.message = exception.getMessage();
       // this.statusCode = exception.getStatusCode().value();
        this.uriRequested = uriRequested;
    }

    public ErrorInfo(int statusCode, String message, String uriRequested, Date timestamp) {
       setMessage(message);
        this.statusCode = statusCode;
        this.uriRequested = uriRequested;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
    private void setMessage(String message) {
        this.message = message;
    }

    public String getUriRequested() {
        return uriRequested;
    }
}
