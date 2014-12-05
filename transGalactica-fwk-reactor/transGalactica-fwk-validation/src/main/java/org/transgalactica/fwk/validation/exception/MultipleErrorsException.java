package org.transgalactica.fwk.validation.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.util.Assert;

//TODO : reflechir le liens entre MultipleError, Errors et cette exception
public class MultipleErrorsException extends BusinessException {
	private static final long serialVersionUID = 1L;

	// private MultipleErrors errors;

	private List<MessageSourceResolvable> errors = new ArrayList<MessageSourceResolvable>(2);

	public MultipleErrorsException() {
	}

	public MultipleErrorsException(List<MessageSourceResolvable> errors) {
		Assert.notNull(errors);
		this.errors.addAll(errors);
	}

	public List<MessageSourceResolvable> getErrors() {
		return errors;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(" MultipleErrorsException : {");
		sb.append(errors.toString());
		sb.append(" } ");
		return sb.toString();
	}
}
