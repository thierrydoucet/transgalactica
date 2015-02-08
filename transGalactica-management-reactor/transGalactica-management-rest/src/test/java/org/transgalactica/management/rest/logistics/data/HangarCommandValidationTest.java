package org.transgalactica.management.rest.logistics.data;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.management.rest.AbstractWebTest;
import org.transgalactica.management.rest.logistics.data.impl.JaxbHangarCommand;

public class HangarCommandValidationTest extends AbstractWebTest {

	@Autowired
	private Validator validator;

	@Test
	public void testValidation() {
		JaxbHangarCommand command = BeanUtils.instantiateClass(JaxbHangarCommand.class);
		command.setLocalisation("localisation");
		command.setNombreEmplacements(1);

		Set<ConstraintViolation<JaxbHangarCommand>> constraintViolations = validator.validate(command);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testValidationKo() {
		JaxbHangarCommand command = BeanUtils.instantiateClass(JaxbHangarCommand.class);
		command.setLocalisation("   ");
		command.setNombreEmplacements(null);

		Set<ConstraintViolation<JaxbHangarCommand>> constraintViolations = validator.validate(command);

		assertEquals(2, constraintViolations.size());
		assertThat(
				constraintViolations.iterator().next().getMessageTemplate(),
				anyOf(is("{javax.validation.constraints.NotNull.message}"),
						is("{org.hibernate.validator.constraints.NotBlank.message}")));
	}
}
