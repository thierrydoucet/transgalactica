package org.transgalactica.management.data.materiel.bo;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.management.data.materiel.bo.HangarSearchCriteria;
import org.transgalactica.management.data.materiel.bo.impl.BasicHangarSearchCriteria;
import org.transgalactica.test.AbstractContextTest;

public class HangarSearchCriteriaValidationTest extends AbstractContextTest {

	@Autowired
	private Validator validator;

	@Test
	public void testValidateLocalisation() throws MalformedURLException {
		HangarSearchCriteria hangar = (HangarSearchCriteria) BeanUtils
				.instantiateClass(BasicHangarSearchCriteria.class);
		hangar.setLocalisationHangar("localisationHangar");

		Set<ConstraintViolation<HangarSearchCriteria>> constraintViolations = validator.validate(hangar);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testValidateLocalisationAbsente() throws MalformedURLException {
		HangarSearchCriteria hangar = (HangarSearchCriteria) BeanUtils
				.instantiateClass(BasicHangarSearchCriteria.class);
		hangar.setLocalisationHangar(null);

		Set<ConstraintViolation<HangarSearchCriteria>> constraintViolations = validator.validate(hangar);

		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testValidateLocalisationVide() throws MalformedURLException {
		HangarSearchCriteria hangar = (HangarSearchCriteria) BeanUtils
				.instantiateClass(BasicHangarSearchCriteria.class);
		hangar.setLocalisationHangar(" \t");

		Set<ConstraintViolation<HangarSearchCriteria>> constraintViolations = validator.validate(hangar);

		assertEquals(1, constraintViolations.size());
	}
}
