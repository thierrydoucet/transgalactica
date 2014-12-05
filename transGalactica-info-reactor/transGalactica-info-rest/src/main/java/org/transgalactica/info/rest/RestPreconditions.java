package org.transgalactica.info.rest;

public class RestPreconditions {
	public static <T> T checkFound(final T resource) {
		if (resource == null) {
			throw new ResourceNotFoundException();
		}
		return resource;
	}
}