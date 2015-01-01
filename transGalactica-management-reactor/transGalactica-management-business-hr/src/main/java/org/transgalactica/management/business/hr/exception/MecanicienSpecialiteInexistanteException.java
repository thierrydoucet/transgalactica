package org.transgalactica.management.business.hr.exception;

import org.transgalactica.fwk.validation.exception.BusinessException;

public class MecanicienSpecialiteInexistanteException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public MecanicienSpecialiteInexistanteException(String nom) {
		super(nom, nom);
	}
}
