package com.pakjivega.libreoffice.csb.exception;

public class FieldEmptyException extends Exception {

	private static final long serialVersionUID = -3444687739037322785L;
	
	public FieldEmptyException(String message) {
        super(message);
    }
	
    public FieldEmptyException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
