package org.transgalactica.management.data.people.bo.validation;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PastValidator implements ConstraintValidator<Past, LocalDate> {

	@Override
	public void initialize(Past constraintAnnotation) {
	}

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		LocalDate date = LocalDate.from(value);
		LocalDate now = LocalDate.now();
		return date.isBefore(now) || date.isEqual(now);
	}
}