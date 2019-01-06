package com.gati.mdm.common.exception.business;

public class ResourceAlreadyFoundException extends RuntimeException {

    private static final long serialVersionUID = -4463940822137963077L;

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResourceAlreadyFoundException(String message) {
        super();
        this.message = message;
    }

}
