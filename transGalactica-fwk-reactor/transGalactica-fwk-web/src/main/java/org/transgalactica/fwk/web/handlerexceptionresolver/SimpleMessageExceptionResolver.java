package org.transgalactica.fwk.web.handlerexceptionresolver;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.transgalactica.fwk.validation.exception.ExceptionUtils;
import org.transgalactica.fwk.validation.exception.MultipleErrorsException;

public class SimpleMessageExceptionResolver extends SimpleMappingExceptionResolver {

	public static final String DEFAULT_EXCEPTION_MESSAGES_ATTRIBUTE = "exceptionMessages";

	private String exceptionMessagesAttribute = DEFAULT_EXCEPTION_MESSAGES_ATTRIBUTE;

	protected SimpleMessageExceptionResolver() {
	}

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView modelAndView = super.doResolveException(request, response, handler, ex);
		if (modelAndView != null && ex != null) {
			addMessages(modelAndView, ex);
		}
		return modelAndView;
	}

	protected void addMessages(ModelAndView modelAndView, Exception ex) {
		if (ex instanceof MultipleErrorsException) {
			MultipleErrorsException errorsException = (MultipleErrorsException) ex;
			modelAndView.addObject(getExceptionMessagesAttribute(), errorsException.getErrors());
		}
		else {
			MessageSourceResolvable message = ExceptionUtils.getMessageSourceResolvable(ex);
			modelAndView.addObject(getExceptionMessagesAttribute(), Collections.singletonList(message));
		}
	}

	/*
	 * Accesseurs
	 */

	public String getExceptionMessagesAttribute() {
		return exceptionMessagesAttribute;
	}

	public void setExceptionMessagesAttribute(String exceptionMessagesAttribute) {
		this.exceptionMessagesAttribute = exceptionMessagesAttribute;
	}
}
