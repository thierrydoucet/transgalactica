package org.transgalactica.fwk.remote.exception;

import org.transgalactica.fwk.validation.exception.BusinessException;

public class UndefinedMessageBusinessException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public UndefinedMessageBusinessException() {
		super("UndefinedMessageBusinessException exception message");
	}
}
