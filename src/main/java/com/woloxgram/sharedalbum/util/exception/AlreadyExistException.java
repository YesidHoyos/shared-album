package com.woloxgram.sharedalbum.util.exception;

public class AlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 8686445836322225936L;

	public AlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public AlreadyExistException(String message) {
		super(message);
	}
}
