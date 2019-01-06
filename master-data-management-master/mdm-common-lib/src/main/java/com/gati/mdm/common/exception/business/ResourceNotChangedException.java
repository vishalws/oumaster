package com.gati.mdm.common.exception.business;

public class ResourceNotChangedException extends RuntimeException {

    private static final long serialVersionUID = -8259181839062756410L;

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResourceNotChangedException(String message) {
        super();
        this.message = message;
    }

}
