package org.transgalactica.management.data.people.bo.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The annotated element must be a date in the past or now. Now is defined as
 * the current time according to the virtual machine.
 * <p/>
 * Supported types are:
 * <ul>
 * <li>{@code java.time.LocalDate}</li>
 * </ul>
 * <p/>
 * {@code null} elements are considered valid.
 *
 * @author Thierry
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { PastValidator.class })
public @interface Past {

	String message() default "{javax.validation.constraints.Past.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * Defines several {@link Past} annotations on the same element.
	 *
	 * @see Past
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		Past[] value();
	}
}
