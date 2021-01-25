package com.perforce.akana.qa.exception;

@SuppressWarnings("serial")
public class TestCodeException extends RuntimeException {
	
	public TestCodeException(String message) {
		super(message);
	}

	public TestCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public TestCodeException(Throwable cause) {
		super(cause);
	}

}
