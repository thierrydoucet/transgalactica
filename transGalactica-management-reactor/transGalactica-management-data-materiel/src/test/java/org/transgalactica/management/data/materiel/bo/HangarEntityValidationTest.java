package org.transgalactica.management.data.materiel.bo;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.impl.JpaHangarEntity;
import org.transgalactica.test.AbstractContextTest;

public class HangarEntityValidationTest extends AbstractContextTest {

	@Autowired
	private Validator validator;

	@Test
	public void testValidateLocalisation() throws MalformedURLException {
		HangarEntity hangar = (HangarEntity) BeanUtils.instantiateClass(JpaHangarEntity.class);
		hangar.setLocalisation("localisation");
		hangar.setNombreEmplacements(10);

		Set<ConstraintViolation<HangarEntity>> constraintViolations = validator.validate(hangar);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testValidateLocalisationAbsente() throws MalformedURLException {
		HangarEntity hangar = (HangarEntity) BeanUtils.instantiateClass(JpaHangarEntity.class);
		hangar.setLocalisation(null);
		hangar.setNombreEmplacements(10);

		Set<ConstraintViolation<HangarEntity>> constraintViolations = validator.validate(hangar);

		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testValidateLocalisationVide() throws MalformedURLException {
		HangarEntity hangar = (HangarEntity) BeanUtils.instantiateClass(JpaHangarEntity.class);
		hangar.setLocalisation(" \t");
		hangar.setNombreEmplacements(10);

		Set<ConstraintViolation<HangarEntity>> constraintViolations = validator.validate(hangar);

		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testValidateNombreEmplacement0() throws MalformedURLException {
		HangarEntity hangar = (HangarEntity) BeanUtils.instantiateClass(JpaHangarEntity.class);
		hangar.setLocalisation("localisation");
		hangar.setNombreEmplacements(0);

		Set<ConstraintViolation<HangarEntity>> constraintViolations = validator.validate(hangar);

		assertEquals(1, constraintViolations.size());
	}
}
