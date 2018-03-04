package com.rest.exception;

public class RestException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public RestException(String message, Throwable parent) {
        super(message, parent);
    }

    public RestException(String message) {
    	super(message);
    }

    public RestException(Throwable t) {
        super(t);
    }
}
