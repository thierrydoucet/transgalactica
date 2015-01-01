package org.transgalactica.management.business.logistics.exception;

import org.transgalactica.fwk.validation.exception.BusinessException;

public class HangarInexistantException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public HangarInexistantException(Long identifiant) {
		super(String.valueOf(identifiant), identifiant);
	}
}
