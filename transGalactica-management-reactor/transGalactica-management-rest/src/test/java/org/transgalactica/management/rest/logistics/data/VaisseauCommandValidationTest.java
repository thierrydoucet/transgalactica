package org.transgalactica.management.rest.logistics.data;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.management.rest.AbstractWebTest;
import org.transgalactica.management.rest.logistics.data.impl.JaxbVaisseauCommand;

public class VaisseauCommandValidationTest extends AbstractWebTest {

	@Autowired
	private Validator validator;

	@Test
	public void testValidation() {
		JaxbVaisseauCommand command = BeanUtils.instantiateClass(JaxbVaisseauCommand.class);
		command.setModele("modele");
		command.setImmatriculation("immatriculation");

		Set<ConstraintViolation<JaxbVaisseauCommand>> constraintViolations = validator.validate(command);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testValidationKo() {
		JaxbVaisseauCommand command = BeanUtils.instantiateClass(JaxbVaisseauCommand.class);
		command.setModele("   ");
		command.setImmatriculation("   ");

		Set<ConstraintViolation<JaxbVaisseauCommand>> constraintViolations = validator.validate(command);

		assertEquals(2, constraintViolations.size());
		assertEquals("{org.hibernate.validator.constraints.NotBlank.message}", constraintViolations.iterator().next()
				.getMessageTemplate());
	}
}
