package org.transgalactica.fwk.validation.exception;

public class TechnicalException extends AbstractException {
	private static final long serialVersionUID = 1L;

	public TechnicalException() {
	}

	public TechnicalException(Object... args) {
		super(args);
	}

	public TechnicalException(String msg) {
		super(msg);
	}

	public TechnicalException(Throwable cause) {
		super(cause);
	}

	public TechnicalException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public TechnicalException(String msg, Object... args) {
		super(msg, args);
	}

	public TechnicalException(Throwable cause, Object... args) {
		super(cause, args);
	}

	public TechnicalException(String msg, Throwable cause, Object... args) {
		super(msg, cause, args);
	}
}
