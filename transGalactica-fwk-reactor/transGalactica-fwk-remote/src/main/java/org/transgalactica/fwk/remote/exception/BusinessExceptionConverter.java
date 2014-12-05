package org.transgalactica.fwk.remote.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.transgalactica.fwk.validation.exception.BusinessException;
import org.transgalactica.fwk.validation.exception.ExceptionUtils;
import org.transgalactica.fwk.validation.exception.MultipleErrorsException;

public class BusinessExceptionConverter implements MessageSourceAware {

	private MessageSource messageSource;

	public BusinessExceptionConverter() {
	}

	public RemoteBusinessException convert(BusinessException e) {
		String[] errorCodes = ExceptionUtils.getErrorCodes(e);
		String errorMessage = getErrorMessage(e);
		return new RemoteBusinessException(e.getClass().getName(), errorCodes, errorMessage);
	}

	protected String getErrorMessage(BusinessException e) {
		if (e instanceof MultipleErrorsException) {
			return getErrorMessage((MultipleErrorsException) e);
		}
		MessageSourceResolvable msr = ExceptionUtils.getMessageSourceResolvable(e);
		return messageSource.getMessage(msr, LocaleContextHolder.getLocale());

	}

	protected String getErrorMessage(MultipleErrorsException e) {
		StringBuffer message = new StringBuffer();
		for (MessageSourceResolvable msr : e.getErrors()) {
			message.append(messageSource.getMessage(msr, LocaleContextHolder.getLocale()));
			message.append("\n");
		}
		return message.toString();
	}

	/*
	 * IOC
	 */

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
