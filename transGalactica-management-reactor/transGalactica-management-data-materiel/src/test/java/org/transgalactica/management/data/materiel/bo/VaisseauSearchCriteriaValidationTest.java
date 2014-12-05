package org.transgalactica.management.data.materiel.bo;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.impl.BasicVaisseauSearchCriteria;
import org.transgalactica.test.AbstractContextTest;

public class VaisseauSearchCriteriaValidationTest extends AbstractContextTest {

	@Autowired
	private Validator validator;

	@Test
	public void testValidate() throws MalformedURLException {
		VaisseauSearchCriteria vaisseau = (VaisseauSearchCriteria) BeanUtils
				.instantiateClass(BasicVaisseauSearchCriteria.class);
		vaisseau.setImmatriculation("immatriculation");
		vaisseau.setModele("modele");

		Set<ConstraintViolation<VaisseauSearchCriteria>> constraintViolations = validator.validate(vaisseau);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testValidateImmatriculationAbsente() throws MalformedURLException {
		VaisseauSearchCriteria vaisseau = (VaisseauSearchCriteria) BeanUtils
				.instantiateClass(BasicVaisseauSearchCriteria.class);
		vaisseau.setImmatriculation(null);
		vaisseau.setModele("modele");

		Set<ConstraintViolation<VaisseauSearchCriteria>> constraintViolations = validator.validate(vaisseau);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testValidateImmatriculationVide() throws MalformedURLException {
		VaisseauSearchCriteria vaisseau = (VaisseauSearchCriteria) BeanUtils
				.instantiateClass(BasicVaisseauSearchCriteria.class);
		vaisseau.setImmatriculation(" \t ");
		vaisseau.setModele("modele");

		Set<ConstraintViolation<VaisseauSearchCriteria>> constraintViolations = validator.validate(vaisseau);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testValidateModeleAbsente() throws MalformedURLException {
		VaisseauSearchCriteria vaisseau = (VaisseauSearchCriteria) BeanUtils
				.instantiateClass(BasicVaisseauSearchCriteria.class);
		vaisseau.setImmatriculation("immatriculation");
		vaisseau.setModele(null);

		Set<ConstraintViolation<VaisseauSearchCriteria>> constraintViolations = validator.validate(vaisseau);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testValidateModeleVide() throws MalformedURLException {
		VaisseauSearchCriteria vaisseau = (VaisseauSearchCriteria) BeanUtils
				.instantiateClass(BasicVaisseauSearchCriteria.class);
		vaisseau.setImmatriculation("immatriculation");
		vaisseau.setModele(" \t\n ");

		Set<ConstraintViolation<VaisseauSearchCriteria>> constraintViolations = validator.validate(vaisseau);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testValidateImmatriculationEtModeleAbsent() throws MalformedURLException {
		VaisseauSearchCriteria vaisseau = (VaisseauSearchCriteria) BeanUtils
				.instantiateClass(BasicVaisseauSearchCriteria.class);
		vaisseau.setImmatriculation(null);
		vaisseau.setModele(null);

		Set<ConstraintViolation<VaisseauSearchCriteria>> constraintViolations = validator.validate(vaisseau);

		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testValidateImmatriculationEtModeleVide() throws MalformedURLException {
		VaisseauSearchCriteria vaisseau = (VaisseauSearchCriteria) BeanUtils
				.instantiateClass(BasicVaisseauSearchCriteria.class);
		vaisseau.setImmatriculation(" \t ");
		vaisseau.setModele(" \t ");

		Set<ConstraintViolation<VaisseauSearchCriteria>> constraintViolations = validator.validate(vaisseau);

		assertEquals(1, constraintViolations.size());
	}
}
