package org.transgalactica.management.data.materiel.bo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;

public class VaisseauSearchCriteriaConstraintValidator implements
		ConstraintValidator<VaisseauSearchCriteriaConstraint, VaisseauSearchCriteria> {

	public VaisseauSearchCriteriaConstraintValidator() {
	}

	@Override
	public void initialize(VaisseauSearchCriteriaConstraint constraintAnnotation) {
		// nop
	}

	@Override
	public boolean isValid(VaisseauSearchCriteria criteres, ConstraintValidatorContext context) {
		return criteres == null || StringUtils.isNotBlank(criteres.getImmatriculation())
				|| StringUtils.isNotBlank(criteres.getModele());
	}
}
