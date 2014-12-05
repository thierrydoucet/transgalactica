package org.transgalactica.info.data.motd.repository;

public class MotdReadException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MotdReadException(String message, Throwable cause) {
		super(message, cause);
	}
}
