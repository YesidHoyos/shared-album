package com.woloxgram.sharedalbum.util.exception;

public class NullFieldException extends RuntimeException {

	private static final long serialVersionUID = 782505933380524478L;

	public NullFieldException(String message, Throwable cause) {
		super(message, cause);
	}

	public NullFieldException(String message) {
		super(message);
	}
}
