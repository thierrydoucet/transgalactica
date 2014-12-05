package org.transgalactica.fwk.validation.exception;

public class BusinessException extends AbstractException {
	private static final long serialVersionUID = 1L;

	public BusinessException() {
	}

	public BusinessException(Object... args) {
		super(args);
	}

	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BusinessException(String msg, Object... args) {
		super(msg, args);
	}

	public BusinessException(Throwable cause, Object... args) {
		super(cause, args);
	}

	public BusinessException(String msg, Throwable cause, Object... args) {
		super(msg, cause, args);
	}
}
