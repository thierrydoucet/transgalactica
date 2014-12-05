package org.transgalactica.management.rest.hr.data;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.transgalactica.management.rest.AbstractSpringContextTest;
import org.transgalactica.management.rest.hr.data.impl.JaxbEmployeCommand;

public class EmployeCommandValidationTest extends AbstractSpringContextTest {

	@Autowired
	@Qualifier("javax.validation.ValidatorFactory")
	private Validator validator;

	@Test
	public void testValidation() {
		JaxbEmployeCommand command = BeanUtils.instantiateClass(JaxbEmployeCommand.class);
		command.setNom("localisation");

		Set<ConstraintViolation<JaxbEmployeCommand>> constraintViolations = validator.validate(command);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testValidationKo() {
		JaxbEmployeCommand command = BeanUtils.instantiateClass(JaxbEmployeCommand.class);
		command.setNom("   ");

		Set<ConstraintViolation<JaxbEmployeCommand>> constraintViolations = validator.validate(command);

		assertEquals(1, constraintViolations.size());
		assertThat(constraintViolations.iterator().next().getMessageTemplate(),
				is("{org.hibernate.validator.constraints.NotBlank.message}"));
	}
}
