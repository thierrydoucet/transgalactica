package org.transgalactica.fwk.validation.exception;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;
import org.springframework.context.MessageSourceResolvable;
import org.transgalactica.fwk.validation.exception.AbstractException;
import org.transgalactica.fwk.validation.exception.BusinessException;
import org.transgalactica.fwk.validation.exception.ExceptionUtils;
import org.transgalactica.fwk.validation.exception.MultipleErrorsException;
import org.transgalactica.fwk.validation.exception.TechnicalException;


public class ExceptionUtilsTest {

	@Test
	public void testExceptionCodes() {
		String[] codes = ExceptionUtils.getErrorCodes(new Exception());
		assertEquals(1, codes.length);
		assertEquals("java.lang.Exception", codes[0]);
	}

	@Test
	public void testTechnicalExceptionCodes() {
		String[] codes = ExceptionUtils.getErrorCodes(new TechnicalException());
		assertEquals(4, codes.length);
		assertEquals(TechnicalException.class.getName(), codes[0]);
		assertEquals(AbstractException.class.getName(), codes[1]);
		assertEquals(RuntimeException.class.getName(), codes[2]);
		assertEquals(Exception.class.getName(), codes[3]);
	}

	@Test
	public void testBusinessExceptionCodes() {
		String[] codes = ExceptionUtils.getErrorCodes(new BusinessException());
		assertEquals(4, codes.length);
		assertEquals(BusinessException.class.getName(), codes[0]);
		assertEquals(AbstractException.class.getName(), codes[1]);
		assertEquals(RuntimeException.class.getName(), codes[2]);
		assertEquals(Exception.class.getName(), codes[3]);
	}

	@Test
	public void testMultipleExceptionCodes() {
		String[] codes = ExceptionUtils.getErrorCodes(new MultipleErrorsException());
		assertEquals(5, codes.length);
		assertEquals(MultipleErrorsException.class.getName(), codes[0]);
		assertEquals(BusinessException.class.getName(), codes[1]);
		assertEquals(AbstractException.class.getName(), codes[2]);
		assertEquals(RuntimeException.class.getName(), codes[3]);
		assertEquals(Exception.class.getName(), codes[4]);
	}

	@Test
	public void testEmptyCauseMessage() {
		String m = ExceptionUtils.getCauseMessage(new TechnicalException(new FileNotFoundException()));
		assertEquals(FileNotFoundException.class.getName(), m);
	}

	@Test
	public void testNullCauseMessage() {
		String m = ExceptionUtils.getCauseMessage(new TechnicalException(new FileNotFoundException(null)));
		assertEquals(FileNotFoundException.class.getName(), m);
	}

	@Test
	public void testSimpleCauseMessage() {
		String s = "bla bla";
		String m = ExceptionUtils.getCauseMessage(new TechnicalException(new FileNotFoundException(s)));
		assertEquals(s, m);
	}

	@Test
	public void testExceptionArguments() {
		Object[] args = ExceptionUtils.getArguments(new Exception());
		assertEquals(0, args.length);
	}

	@Test
	public void testBusinessExceptionWitoutArguments() {
		Object[] args = ExceptionUtils.getArguments(new BusinessException());
		assertEquals(0, args.length);
	}

	@Test
	public void testBusinessExceptionWithArguments() {
		BusinessException bex = new BusinessException();
		Object arg1 = "arg1";
		Object arg2 = "arg2";
		bex.addArgument(arg1);
		bex.addArgument(arg2);
		Object[] args = ExceptionUtils.getArguments(bex);
		assertEquals(2, args.length);
		assertEquals(arg1, args[0]);
		assertEquals(arg2, args[1]);
	}

	@Test
	public void testBusinessExceptionWithArgumentsAndMessage() {
		BusinessException bex = new BusinessException("RG_TEST");
		bex.addArgument("arg1");
		bex.addArgument("arg2");
		Object[] args = ExceptionUtils.getArguments(bex);
		assertEquals(3, args.length);
		assertEquals("RG_TEST", ((MessageSourceResolvable) args[0]).getCodes()[0]);
		assertEquals("arg1", args[1]);
		assertEquals("arg2", args[2]);
	}

	@Test
	public void testNullPointerExceptionArguments() {
		NullPointerException nex = new NullPointerException();
		nex.fillInStackTrace();

		Object[] args = ExceptionUtils.getArguments(nex);
		assertEquals(2, args.length);
		assertEquals(this.getClass().getName() + ".class", args[0]);
		assertEquals(Integer.class, args[1].getClass());
	}
}
