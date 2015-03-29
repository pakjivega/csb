package com.pakjivega.libreoffice.csb.exception;

public class CCCInvalidException extends Exception {

	private static final long serialVersionUID = 6193190615887069886L;

	public CCCInvalidException(String message) {
        super(message);
    }

    public CCCInvalidException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
