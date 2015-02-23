package org.transgalactica.management.data.people.bo.validation;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.Test;

public class PastTest {

	private PastTestBean bean;

	@Before
	public void setup() {
		bean = new PastTestBean();
	}

	@Test
	public void thatNullIsValid() {
		Set<ConstraintViolation<PastTestBean>> violations = validateClass(bean);
		assertEquals(violations.size(), 0);
	}

	@Test
	public void thatYesterdayIsValid() throws Exception {
		bean.setDate(LocalDate.now().minusDays(1));
		Set<ConstraintViolation<PastTestBean>> violations = validateClass(bean);
		assertEquals(violations.size(), 0);
	}

	@Test
	public void thatTodayIsValid() throws Exception {
		bean.setDate(LocalDate.now());
		Set<ConstraintViolation<PastTestBean>> violations = validateClass(bean);
		assertEquals(violations.size(), 0);
	}

	@Test
	public void thatTomorrowIsInvalid() throws Exception {
		bean.setDate(LocalDate.now().plusDays(1));
		Set<ConstraintViolation<PastTestBean>> violations = validateClass(bean);
		assertEquals(violations.size(), 1);
	}

	private Set<ConstraintViolation<PastTestBean>> validateClass(PastTestBean myClass) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		return validator.validate(myClass);
	}

	public static class PastTestBean {

		@Past
		private LocalDate date;

		public void setDate(LocalDate date) {
			this.date = date;
		}
	}
}