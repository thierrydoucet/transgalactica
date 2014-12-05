package org.transgalactica.management.business.hr.exception;

import org.transgalactica.fwk.validation.exception.BusinessException;

public class EmployeInexistantException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public EmployeInexistantException(Long matricule) {
		super(matricule);
	}
}
