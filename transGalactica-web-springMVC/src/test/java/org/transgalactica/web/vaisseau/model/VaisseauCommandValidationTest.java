package org.transgalactica.web.vaisseau.model;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.web.AbstractWebTest;

public class VaisseauCommandValidationTest extends AbstractWebTest {

	@Autowired
	private Validator validator;

	@Test
	public void testValidation() {
		VaisseauCommand command = BeanUtils.instantiateClass(VaisseauCommand.class);
		command.setModele("modele");
		command.setImmatriculation("immatriculation");

		Set<ConstraintViolation<VaisseauCommand>> constraintViolations = validator.validate(command);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testValidationKo() {
		VaisseauCommand command = BeanUtils.instantiateClass(VaisseauCommand.class);
		command.setModele("   ");
		command.setImmatriculation("   ");

		Set<ConstraintViolation<VaisseauCommand>> constraintViolations = validator.validate(command);

		assertEquals(2, constraintViolations.size());
		assertEquals("{org.hibernate.validator.constraints.NotBlank.message}", constraintViolations.iterator().next()
				.getMessageTemplate());
	}
}
