package org.transgalactica.fwk.validation.exception;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.transgalactica.fwk.validation.exception.MultipleErrorsException;


public class MultipleErrorExceptionTest {

	@Test
	public void testConstructeur() {
		MessageSourceResolvable msr = new DefaultMessageSourceResolvable("toto");
		MultipleErrorsException exception = new MultipleErrorsException(Collections.singletonList(msr));
		Assert.assertEquals("toto", exception.getErrors().get(0).getCodes()[0]);
	}
}
