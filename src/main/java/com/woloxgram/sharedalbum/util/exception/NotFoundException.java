package com.woloxgram.sharedalbum.util.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8686445836322225939L;

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message) {
		super(message);
	}
}
