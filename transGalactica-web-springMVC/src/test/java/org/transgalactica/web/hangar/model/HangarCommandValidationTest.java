package org.transgalactica.web.hangar.model;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.web.AbstractWebTest;

public class HangarCommandValidationTest extends AbstractWebTest {

	@Autowired
	private Validator validator;

	@Test
	public void testValidation() {
		HangarCommand command = BeanUtils.instantiateClass(HangarCommand.class);
		command.setLocalisation("localisation");
		command.setNombreEmplacements(1);

		Set<ConstraintViolation<HangarCommand>> constraintViolations = validator.validate(command);

		assertEquals(0, constraintViolations.size());
	}

	public void testValidationKo() {
		HangarCommand command = BeanUtils.instantiateClass(HangarCommand.class);
		command.setLocalisation("   ");
		command.setNombreEmplacements(null);

		Set<ConstraintViolation<HangarCommand>> constraintViolations = validator.validate(command);

		assertEquals(2, constraintViolations.size());
		assertEquals(
				constraintViolations.iterator().next().getMessage(),
				anyOf(is("{javax.validation.constraints.NotNull.message}"),
						is("{org.hibernate.validator.constraints.NotBlank.message}")));
	}
}
