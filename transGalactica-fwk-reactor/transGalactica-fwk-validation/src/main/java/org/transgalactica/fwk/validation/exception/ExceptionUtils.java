package org.transgalactica.fwk.validation.exception;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;

public abstract class ExceptionUtils {

	protected ExceptionUtils() {
	}

	/**
	 * Look for the message of the cause of the specified exception. If there is
	 * no root cause the message of the specified exception is returned.
	 * 
	 * @param exception : Exception to look for message cause
	 * @return Message of the cause of the specified exception
	 */
	public static String getCauseMessage(Throwable exception) {
		Throwable throwable = org.apache.commons.lang3.exception.ExceptionUtils.getRootCause(exception);
		if (throwable != null && !StringUtils.isBlank(throwable.getMessage())) {
			return convertExceptionToString(throwable);
		}
		return convertExceptionToString(exception);
	}

	private static String convertExceptionToString(Throwable t) {
		return t.getLocalizedMessage();
	}

	/**
	 * Build a list of error codes using exception class hierarchy
	 */
	public static String[] getErrorCodes(Throwable exception) {
		List<String> codes = new ArrayList<String>();
		Class<?> classOfException = exception.getClass();
		codes.add(classOfException.getName());

		Class<?> superClass = classOfException.getSuperclass();
		while (superClass != null && !Throwable.class.equals(superClass)) {
			codes.add(superClass.getName());
			superClass = superClass.getSuperclass();
		}
		return codes.toArray(new String[codes.size()]);
	}

	public static boolean isBusinessException(Throwable t) {
		if (t == null) {
			return false;
		}
		return t instanceof BusinessException;
	}

	public static Object[] getArguments(Throwable exception) {
		List<Object> args = new ArrayList<Object>();

		String causeMessage = getCauseMessage(exception);
		if (StringUtils.isNotBlank(causeMessage)) {
			args.add(new DefaultMessageSourceResolvable(new String[] { causeMessage }, causeMessage));
		}
		if (exception instanceof AbstractException) {
			// Is there some arguments passed with the exception ?
			args.addAll(((AbstractException) exception).getArguments());
		}
		else if (exception instanceof NullPointerException || exception instanceof ClassCastException) {
			StackTraceElement[] stack = exception.getStackTrace();
			if (stack != null && stack.length > 0) {
				args.add(stack[0].getClassName() + ".class");
				args.add(stack[0].getLineNumber());
			}
		}
		return args.toArray();
	}

	public static MessageSourceResolvable getMessageSourceResolvable(Throwable exception) {
		String[] codes = getErrorCodes(exception);
		Object[] args = getArguments(exception);
		return new DefaultMessageSourceResolvable(codes, args);
	}
}
