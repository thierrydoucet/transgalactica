package org.transgalactica.fwk.validation.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.transgalactica.fwk.validation.exception.TechnicalException;


public class TechnicalExceptionTest {

	@Test
	public void testConstructeur() {
		TechnicalException e = new TechnicalException();
		assertNull(e.getMessage());
		assertNull(e.getCause());
		assertTrue(e.getArguments().isEmpty());
	}
	
	@Test
	public void testConstructeurMessage() {
		TechnicalException e = new TechnicalException("message");
		assertEquals("message", e.getMessage());
		assertNull(e.getCause());
		assertTrue(e.getArguments().isEmpty());
	}

	@Test
	public void testConstructeurCause() {
		Throwable cause = new Throwable();
		TechnicalException e = new TechnicalException(cause);
		assertEquals(cause.toString(), e.getMessage());
		assertSame(cause, e.getCause());
		assertTrue(e.getArguments().isEmpty());
	}
	
	@Test
	public void testConstructeurArguments() {
		TechnicalException e = new TechnicalException(new Object[]{"1"});
		assertNull(e.getMessage());
		assertNull(e.getCause());
		assertEquals("1", e.getArguments().get(0));
	}
	
	@Test
	public void testConstructeurMessageCause() {
		Throwable cause = new Throwable();
		TechnicalException e = new TechnicalException("message", cause);
		assertEquals("message", e.getMessage());
		assertSame(cause, e.getCause());
		assertTrue(e.getArguments().isEmpty());
	}

	@Test
	public void testConstructeurMessageArguments() {
		TechnicalException e = new TechnicalException("message", "1");
		assertEquals("message", e.getMessage());
		assertNull(e.getCause());
		assertEquals("1", e.getArguments().get(0));
	}

	@Test
	public void testConstructeurCauseArguments() {
		Throwable cause = new Throwable();
		TechnicalException e = new TechnicalException(cause, "1");
		assertEquals(cause.toString(), e.getMessage());
		assertSame(cause, e.getCause());
		assertEquals("1", e.getArguments().get(0));
	}

	@Test
	public void testConstructeurMessageCauseArguments() {
		Throwable cause = new Throwable();
		TechnicalException e = new TechnicalException("message", cause, "1");
		assertEquals("message", e.getMessage());
		assertSame(cause, e.getCause());
		assertEquals("1", e.getArguments().get(0));
	}
}
