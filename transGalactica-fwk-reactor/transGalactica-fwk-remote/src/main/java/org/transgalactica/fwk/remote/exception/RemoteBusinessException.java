package org.transgalactica.fwk.remote.exception;

import org.apache.commons.lang3.ArrayUtils;

public class RemoteBusinessException extends Exception {

	private static final long serialVersionUID = -1L;

	private final String[] errorCodes;

	private final String errorMessage;

	protected RemoteBusinessException() {
		this(null, new String[0], null);
	}

	public RemoteBusinessException(String cause) {
		this(cause, new String[0], null);
	}

	public RemoteBusinessException(String cause, String... errorCodes) {
		this(cause, errorCodes, null);
	}

	public RemoteBusinessException(String cause, String[] errorCodes, String errorMessage) {
		super(cause);
		this.errorCodes = ArrayUtils.clone(errorCodes);
		this.errorMessage = errorMessage;
	}

	public String[] getErrorCodes() {
		return errorCodes;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
