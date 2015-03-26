package com.pakjivega.libreoffice.csb;

public class CCCInvalidException extends Exception {
	public CCCInvalidException(String message) {
        super(message);
    }

    public CCCInvalidException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
