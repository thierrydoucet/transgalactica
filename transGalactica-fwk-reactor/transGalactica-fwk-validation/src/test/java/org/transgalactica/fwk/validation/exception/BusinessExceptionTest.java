package org.transgalactica.fwk.validation.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.transgalactica.fwk.validation.exception.BusinessException;


public class BusinessExceptionTest {

	@Test
	public void testConstructeur() {
		BusinessException e = new BusinessException();
		assertNull(e.getMessage());
		assertNull(e.getCause());
		assertTrue(e.getArguments().isEmpty());
	}
	
	@Test
	public void testConstructeurMessage() {
		BusinessException e = new BusinessException("message");
		assertEquals("message", e.getMessage());
		assertNull(e.getCause());
		assertTrue(e.getArguments().isEmpty());
	}

	@Test
	public void testConstructeurCause() {
		Throwable cause = new Throwable();
		BusinessException e = new BusinessException(cause);
		assertEquals(cause.toString(), e.getMessage());
		assertSame(cause, e.getCause());
		assertTrue(e.getArguments().isEmpty());
	}
	
	@Test
	public void testConstructeurArguments() {
		BusinessException e = new BusinessException(new Object[]{"1"});
		assertNull(e.getMessage());
		assertNull(e.getCause());
		assertEquals("1", e.getArguments().get(0));
	}
	
	@Test
	public void testConstructeurMessageCause() {
		Throwable cause = new Throwable();
		BusinessException e = new BusinessException("message", cause);
		assertEquals("message", e.getMessage());
		assertSame(cause, e.getCause());
		assertTrue(e.getArguments().isEmpty());
	}

	@Test
	public void testConstructeurMessageArguments() {
		BusinessException e = new BusinessException("message", "1");
		assertEquals("message", e.getMessage());
		assertNull(e.getCause());
		assertEquals("1", e.getArguments().get(0));
	}

	@Test
	public void testConstructeurCauseArguments() {
		Throwable cause = new Throwable();
		BusinessException e = new BusinessException(cause, "1");
		assertEquals(cause.toString(), e.getMessage());
		assertSame(cause, e.getCause());
		assertEquals("1", e.getArguments().get(0));
	}

	@Test
	public void testConstructeurMessageCauseArguments() {
		Throwable cause = new Throwable();
		BusinessException e = new BusinessException("message", cause, "1");
		assertEquals("message", e.getMessage());
		assertSame(cause, e.getCause());
		assertEquals("1", e.getArguments().get(0));
	}
}
