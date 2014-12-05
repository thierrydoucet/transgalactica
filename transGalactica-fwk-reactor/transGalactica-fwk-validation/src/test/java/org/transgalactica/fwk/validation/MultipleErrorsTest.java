package org.transgalactica.fwk.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.transgalactica.fwk.validation.exception.AbstractException;
import org.transgalactica.fwk.validation.exception.ExceptionUtils;
import org.transgalactica.fwk.validation.exception.MultipleErrorsException;

@ContextConfiguration(locations = { "classpath:org/transgalactica/fwk/validation/MultipleErrorsTest.spring.xml" })
public class MultipleErrorsTest extends AbstractJUnit4SpringContextTests {

	private static final String ERROR_CODE = "error.code";

	private static final String OBJECT_NAME = "object.name";

	private static final MessageCodesResolver RESOLVER = new DefaultMessageCodesResolver();

	private class MockException extends AbstractException {

		private static final long serialVersionUID = 1L;

		public MockException(String message, Object... args) {
			super(message);
			for (int i = 0; i < args.length; i++) {
				addArgument(args[i]);
			}
		}
	}

	@Test
	public void testEmptyError() {
		MultipleErrors errors = new MultipleErrors();
		assertFalse(errors.hasErrors());
		assertNotNull(errors.getAllErrorMessages());
		assertEquals(0, errors.getAllErrorMessages().size());
	}

	@Test
	public void testSingleError() {
		MultipleErrors errors = new MultipleErrors();
		MessageSourceResolvable m = createMessage(1);
		errors.add(m);

		checkMessages(errors, new MessageSourceResolvable[] { m });
	}

	@Test
	public void testTwoErrors() {
		MultipleErrors errors = new MultipleErrors();
		MessageSourceResolvable m1 = createMessage(1);
		MessageSourceResolvable m2 = createMessage(2);
		errors.add(m1);
		errors.add(m2);

		checkMessages(errors, new MessageSourceResolvable[] { m1, m2 });
	}

	@Test
	public void testTwoStringErrors() {
		MultipleErrors errors = new MultipleErrors();
		MessageSourceResolvable m1 = new DefaultMessageSourceResolvable(new String[] { "message1" }, "message1");
		MessageSourceResolvable m2 = new DefaultMessageSourceResolvable(new String[] { "message2" }, "message2");
		errors.add("message1");
		errors.add("message2");

		checkMessages(errors, new MessageSourceResolvable[] { m1, m2 });
	}

	@Test
	public void testAddMulitpleErrorsToEmptyList() {
		MultipleErrors errors1 = new MultipleErrors();

		MultipleErrors errors2 = new MultipleErrors();
		MessageSourceResolvable m2 = createMessage(2);
		errors2.add(m2);

		errors1.add(errors2);

		checkMessages(errors1, new MessageSourceResolvable[] { m2 });
	}

	@Test
	public void testAddMulitpleErrorsToExistingList() {
		MultipleErrors errors1 = new MultipleErrors();
		MessageSourceResolvable m1 = createMessage(1);
		errors1.add(m1);

		MultipleErrors errors2 = new MultipleErrors();
		MessageSourceResolvable m2 = createMessage(2);
		errors2.add(m2);

		errors1.add(errors2);

		checkMessages(errors1, new MessageSourceResolvable[] { m1, m2 });
	}

	@Test
	public void testAddBasicExceptionWithNoMessage() {
		Exception e = new Exception();
		MultipleErrors errors = new MultipleErrors();
		MessageSourceResolvable m = new DefaultMessageSourceResolvable(new String[] { "java.lang.Exception" },
				new Object[] {});

		errors.add(e);

		checkMessages(errors, new MessageSourceResolvable[] { m });
	}

	@Test
	public void testAddExceptionWithOneArgument() {
		Exception e = new MockException("some.message{0}", "some.args");

		MultipleErrors errors = new MultipleErrors();
		MessageSourceResolvable m = new DefaultMessageSourceResolvable(new String[] {//
				MockException.class.getName(), //
						AbstractException.class.getName(), //
						RuntimeException.class.getName(), //
						Exception.class.getName() //
				}, new Object[] {
						new DefaultMessageSourceResolvable(new String[] { ExceptionUtils.getCauseMessage(e) },
								ExceptionUtils.getCauseMessage(e)), "some.args" });

		errors.add(e);

		checkMessages(errors, new MessageSourceResolvable[] { m });
	}

	@Test
	public void testAddExceptionWithTwoArguments() {
		Exception e = new MockException("some.message{0}{1}", "arg1", "arg2");

		MultipleErrors errors = new MultipleErrors();
		MessageSourceResolvable m = new DefaultMessageSourceResolvable(new String[] {//
				MockException.class.getName(), //
						AbstractException.class.getName(), //
						RuntimeException.class.getName(), //
						Exception.class.getName() //
				}, new Object[] {
						new DefaultMessageSourceResolvable(new String[] { ExceptionUtils.getCauseMessage(e) },
								ExceptionUtils.getCauseMessage(e)), "arg1", "arg2" });

		errors.add(e);

		checkMessages(errors, new MessageSourceResolvable[] { m });
	}

	@Test
	public void testAddBasicExceptionWithMessage() {
		Exception e = new Exception("some.message");
		MultipleErrors errors = new MultipleErrors();
		MessageSourceResolvable m = new DefaultMessageSourceResolvable(new String[] { "java.lang.Exception" },
				new Object[] { new DefaultMessageSourceResolvable(new String[] { "some.message" }, "some.message") });

		errors.add(e);

		checkMessages(errors, new MessageSourceResolvable[] { m });
	}

	@Test
	public void testAddEmptyMultipleErrorsException() {
		MultipleErrors errors = new MultipleErrors();

		MultipleErrors errors1 = new MultipleErrors();
		MultipleErrorsException e = new MultipleErrorsException(errors1.getAllErrorMessages());

		errors.add(e);

		assertEquals(0, errors.getAllErrorMessages().size());
	}

	@Test
	public void testAddMultipleErrorsException() {
		MultipleErrors errors = new MultipleErrors();
		Exception npe = new NullPointerException();
		errors.add(npe);
		errors.add(new IllegalAccessException());

		MultipleErrors errors1 = new MultipleErrors();
		errors1.add(new NoSuchElementException());
		MultipleErrorsException e = new MultipleErrorsException(errors1.getAllErrorMessages());

		errors.add(e);

		MessageSourceResolvable m1 = new DefaultMessageSourceResolvable(new String[] {
				NullPointerException.class.getName(), RuntimeException.class.getName(), Exception.class.getName() },
				ExceptionUtils.getArguments(npe));
		MessageSourceResolvable m2 = new DefaultMessageSourceResolvable(new String[] {
				IllegalAccessException.class.getName(), ReflectiveOperationException.class.getName(),
				Exception.class.getName() }, new Object[] {});
		MessageSourceResolvable m3 = new DefaultMessageSourceResolvable(new String[] {
				NoSuchElementException.class.getName(), RuntimeException.class.getName(), Exception.class.getName() },
				new Object[] {});

		checkMessages(errors, new MessageSourceResolvable[] { m1, m2, m3 });
	}

	@Test
	public void testCheckErrorsWithoutError() {
		MultipleErrors errors = new MultipleErrors();
		try {
			errors.checkErrors();
		}
		catch (MultipleErrorsException mex) {
			fail();
		}
	}

	@Test
	public void testCheckErrorsWithError() {
		MultipleErrors errors = new MultipleErrors();
		errors.add(new NullPointerException());
		try {
			errors.checkErrors();
			fail();
		}
		catch (MultipleErrorsException mex) {
			assertEquals(errors.getAllErrorMessages(), mex.getErrors());
		}
	}

	private MessageSourceResolvable createMessage(int index) {
		String[] codes = RESOLVER.resolveMessageCodes(ERROR_CODE, OBJECT_NAME);
		MessageSourceResolvable message = new DefaultMessageSourceResolvable(codes,
				new Integer[] { new Integer(index) });
		return message;
	}

	private void checkMessages(MultipleErrors errors, MessageSourceResolvable[] expectedMessages) {
		if (expectedMessages == null || expectedMessages.length == 0) {
			assertFalse(errors.hasErrors());
		}
		else {
			assertTrue(errors.hasErrors());
			List<MessageSourceResolvable> errs = errors.getAllErrorMessages();
			assertNotNull(errs);
			assertEquals(expectedMessages.length, errs.size());
			for (int i = 0; i < expectedMessages.length; i++) {
				assertEqualsMessage(expectedMessages[i], errs.get(i));
			}
		}
	}

	private void assertEqualsMessage(MessageSourceResolvable expectedMessage, MessageSourceResolvable message) {
		assertEquals(expectedMessage != null, message != null);
		if (expectedMessage != null && !expectedMessage.equals(message)) {
			// check codes
			String[] expectedCodes = expectedMessage.getCodes();
			String[] codes = message.getCodes();
			assertEquals(expectedCodes != null, codes != null);
			if (expectedCodes != null) {
				assertEquals(Arrays.asList(expectedCodes), Arrays.asList(codes));
			}
			// check args
			Object[] expectedArgs = expectedMessage.getArguments();
			Object[] args = message.getArguments();
			assertEquals(expectedArgs != null, args != null);
			if (expectedArgs != null) {
				assertEquals(Arrays.asList(expectedArgs), Arrays.asList(args));
			}
			// check defaultMessage
			assertEquals(expectedMessage.getDefaultMessage(), message.getDefaultMessage());
		}
	}
}
