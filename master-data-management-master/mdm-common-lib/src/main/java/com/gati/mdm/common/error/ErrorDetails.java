package com.gati.mdm.common.error;

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime timestamp;

    private String uri;

    private String message;

    private String details;

    public ErrorDetails(LocalDateTime timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        if (this.timestamp == null) {
            return null;
        }
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {

        if (timestamp == null) {
            this.timestamp = null;
        } else {
            this.timestamp = timestamp;
        }
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ErrorDetails [timestamp=" + timestamp + ", uri=" + uri + ", message=" + message + ", details=" + details
                + "]";
    }
}
