package com.shaobing.wallaby.common.exception;

public class WallabyException extends RuntimeException {
	private static final long serialVersionUID = -313045249805613003L;

	public WallabyException() {
	}

	public WallabyException(String message, Throwable cause) {
		super(message, cause);
	}

	public WallabyException(String message) {
		super(message);
	}

	public WallabyException(Throwable cause) {
		super(cause);
	}

	
}
