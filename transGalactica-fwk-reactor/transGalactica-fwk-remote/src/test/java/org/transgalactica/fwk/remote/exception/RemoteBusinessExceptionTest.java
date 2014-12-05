package org.transgalactica.fwk.remote.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.transgalactica.fwk.remote.exception.RemoteBusinessException;
import org.transgalactica.fwk.validation.exception.BusinessException;

public class RemoteBusinessExceptionTest {

	@Test
	public void testConstructeurVide() {
		RemoteBusinessException e = BeanUtils.instantiateClass(RemoteBusinessException.class);
		assertNull(e.getMessage());
		assertEquals(0, e.getErrorCodes().length);
		assertNull(e.getErrorMessage());
	}

	@Test
	public void testConstructeurCause() {
		RemoteBusinessException e = new RemoteBusinessException("test:" + new BusinessException().getClass().getName());
		assertEquals("test:org.transgalactica.fwk.validation.exception.BusinessException", e.getMessage());
		assertEquals(0, e.getErrorCodes().length);
		assertNull(e.getErrorMessage());
	}

	@Test
	public void testConstructeurCauseCode() {
		RemoteBusinessException e = new RemoteBusinessException("test:" + new BusinessException().getClass().getName(),
				new String[] { "code" });
		assertEquals("test:org.transgalactica.fwk.validation.exception.BusinessException", e.getMessage());
		assertEquals(1, e.getErrorCodes().length);
		assertEquals("code", e.getErrorCodes()[0]);
		assertNull(e.getErrorMessage());
	}

	@Test
	public void testConstructeurCauseCodeMessage() {
		RemoteBusinessException e = new RemoteBusinessException("test:" + new BusinessException().getClass().getName(),
				new String[] { "code", "code secondaire" }, "mon message");
		Assert.assertEquals("test:org.transgalactica.fwk.validation.exception.BusinessException", e.getMessage());
		Assert.assertEquals(2, e.getErrorCodes().length);
		Assert.assertEquals("code", e.getErrorCodes()[0]);
		Assert.assertEquals("code secondaire", e.getErrorCodes()[1]);
		Assert.assertEquals("mon message", e.getErrorMessage());
	}
}
