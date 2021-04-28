package com.woloxgram.sharedalbum.util.exception;

public class AccessTypeNotAllowed extends RuntimeException {

	private static final long serialVersionUID = 8686445836322225938L;

	public AccessTypeNotAllowed(String message, Throwable cause) {
		super(message, cause);
	}

	public AccessTypeNotAllowed(String message) {
		super(message);
	}
}
