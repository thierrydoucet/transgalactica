package org.transgalactica.fwk.validation.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private List<? super Object> arguments;

	public AbstractException() {
	}

	public AbstractException(String msg) {
		super(msg);
	}

	public AbstractException(Throwable cause) {
		super(cause);
	}

	public AbstractException(Object... args) {
		arguments = Arrays.asList(args);
	}

	public AbstractException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public AbstractException(String msg, Object... args) {
		super(msg);
		arguments = Arrays.asList(args);
	}

	public AbstractException(Throwable cause, Object... args) {
		super(cause);
		arguments = Arrays.asList(args);
	}

	public AbstractException(String msg, Throwable cause, Object... args) {
		super(msg, cause);
		arguments = Arrays.asList(args);
	}

	public List<? super Object> getArguments() {
		if (arguments == null) {
			arguments = new ArrayList<Object>(2);
		}
		return arguments;
	}

	public void addArgument(Object argument) {
		getArguments().add(argument);
	}
}
