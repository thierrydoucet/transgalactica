package org.transgalactica.management.data.materiel.bo.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VaisseauSearchCriteriaConstraintValidator.class)
@Documented
public @interface VaisseauSearchCriteriaConstraint {

	String message() default "{org.transgalactica.management.data.materiel.bo.validation.VaisseauSearchCriteriaConstraint}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
