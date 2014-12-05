package org.transgalactica.fwk.remote.exception;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.transgalactica.fwk.validation.MultipleErrors;
import org.transgalactica.fwk.validation.exception.BusinessException;
import org.transgalactica.fwk.validation.exception.MultipleErrorsException;

@ContextConfiguration(locations = { "classpath:org/transgalactica/fwk/remote/exception/BusinessExceptionConverterTest.spring.xml" })
public class BusinessExceptionConverterTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private BusinessExceptionConverter businessExceptionConverter;

	@Test
	public void testConvertionBusinessException() {
		RemoteBusinessException e = businessExceptionConverter.convert(new BusinessException());
		assertEquals("org.transgalactica.fwk.validation.exception.BusinessException", e.getMessage());
		assertArrayEquals(new String[] { "org.transgalactica.fwk.validation.exception.BusinessException",
				"org.transgalactica.fwk.validation.exception.AbstractException", "java.lang.RuntimeException",
				"java.lang.Exception" }, e.getErrorCodes());
		assertEquals("Alligators rock!", e.getErrorMessage());
	}

	@Test
	public void testConvertionUndefinedMessageBusinessException() {
		RemoteBusinessException e = businessExceptionConverter.convert(new UndefinedMessageBusinessException());
		assertEquals("Alligators rock!", e.getErrorMessage());
	}

	@Test
	public void testConvertionMultipleErrorException() {
		MultipleErrors errors = new MultipleErrors();
		errors.add(new BusinessException());
		errors.add(new Exception());
		RemoteBusinessException e = businessExceptionConverter.convert(new MultipleErrorsException(errors
				.getAllErrorMessages()));
		assertEquals("org.transgalactica.fwk.validation.exception.MultipleErrorsException", e.getMessage());
		assertArrayEquals(new String[] { "org.transgalactica.fwk.validation.exception.MultipleErrorsException",
				"org.transgalactica.fwk.validation.exception.BusinessException",
				"org.transgalactica.fwk.validation.exception.AbstractException", "java.lang.RuntimeException",
				"java.lang.Exception" }, e.getErrorCodes());
		assertEquals("Alligators rock!\nPenguins rock!\n", e.getErrorMessage());
	}
}
