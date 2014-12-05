package org.transgalactica.fwk.validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.util.Assert;
import org.transgalactica.fwk.validation.exception.ExceptionUtils;
import org.transgalactica.fwk.validation.exception.MultipleErrorsException;

public class MultipleErrors implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<MessageSourceResolvable> errors = new ArrayList<MessageSourceResolvable>(2);

	public MultipleErrors() {
	}

	public void add(String messageCode) {
		Assert.hasText(messageCode);
		errors.add(new DefaultMessageSourceResolvable(new String[] { messageCode }, messageCode));
	}

	public void add(List<MessageSourceResolvable> messages) {
		Assert.notNull(messages);
		errors.addAll(messages);
	}

	public void add(MessageSourceResolvable... messages) {
		Assert.notNull(messages);
		errors.addAll(Arrays.asList(messages));
	}

	public void add(MultipleErrors someErrors) {
		if (someErrors != null) {
			errors.addAll(someErrors.getAllErrorMessages());
		}
	}

	/**
	 * <p>
	 * Convert an exception into an error message and add it to the error list.
	 * <ul>
	 * <li>If the exception class is <code>MultipleErrorsException</code> then
	 * get the error messages and simply concatenate it to the current list.</li>
	 * <li>For other AbstractException, the error is built on arguments and
	 * class hierarchy.</li>
	 * <li>For other exception, the error is built on message and class
	 * hierarchy.</li>
	 * </ul>
	 */
	public void add(Exception e) {
		Assert.notNull(e);
		if (e instanceof MultipleErrorsException) {
			MultipleErrorsException mex = (MultipleErrorsException) e;
			add(mex.getErrors());
		}
		else {
			MessageSourceResolvable message = ExceptionUtils.getMessageSourceResolvable(e);
			add(message);
		}
	}

	public boolean hasErrors() {
		return !errors.isEmpty();
	}

	public List<MessageSourceResolvable> getAllErrorMessages() {
		return errors;
	}

	public void clearErrors() {
		errors.clear();
	}

	public void checkErrors() {
		if (hasErrors()) {
			throw new MultipleErrorsException(getAllErrorMessages());
		}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("MultipleErrors : { ");
		for (MessageSourceResolvable error : errors) {
			sb.append(error.toString());
			sb.append(',');
		}
		sb.append("} ");
		return sb.toString();
	}
}
