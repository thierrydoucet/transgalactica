package org.transgalactica.management.business.logistics.exception;

import org.transgalactica.fwk.validation.exception.BusinessException;

public class VaisseauInexistantException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public VaisseauInexistantException(String immatriculation) {
		super(immatriculation, immatriculation);
	}
}
