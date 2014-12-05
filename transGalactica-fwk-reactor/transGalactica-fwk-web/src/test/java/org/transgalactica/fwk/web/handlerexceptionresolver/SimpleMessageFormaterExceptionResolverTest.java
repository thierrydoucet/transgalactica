package org.transgalactica.fwk.web.handlerexceptionresolver;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.transgalactica.fwk.validation.MultipleErrors;
import org.transgalactica.fwk.validation.exception.MultipleErrorsException;
import org.transgalactica.fwk.validation.exception.TechnicalException;
import org.transgalactica.fwk.web.handlerexceptionresolver.SimpleMessageExceptionResolver;

public class SimpleMessageFormaterExceptionResolverTest {

	@Test
	public void testAddMessageTechnicalException() {
		TechnicalException exception = new TechnicalException("code");
		ModelAndView modelAndView = new ModelAndView();
		callAddMessage(modelAndView, exception);

		List<MessageSourceResolvable> messages = (List<MessageSourceResolvable>) modelAndView.getModelMap().get(
				SimpleMessageExceptionResolver.DEFAULT_EXCEPTION_MESSAGES_ATTRIBUTE);
		assertEquals(1, messages.size());
		assertEquals("code", ((MessageSourceResolvable) messages.get(0).getArguments()[0]).getCodes()[0]);
	}

	@Test
	public void testAddMessageMultipleErrorsExceptions() {
		MultipleErrors errors = new MultipleErrors();
		errors.add("message1");
		errors.add("message2");
		MultipleErrorsException exception = new MultipleErrorsException(errors.getAllErrorMessages());
		ModelAndView modelAndView = new ModelAndView();
		callAddMessage(modelAndView, exception);

		List<MessageSourceResolvable> messages = (List<MessageSourceResolvable>) modelAndView.getModelMap().get(
				SimpleMessageExceptionResolver.DEFAULT_EXCEPTION_MESSAGES_ATTRIBUTE);
		assertEquals(2, messages.size());
		assertEquals("message1", messages.get(0).getCodes()[0]);
		assertEquals("message2", messages.get(1).getCodes()[0]);
	}

	private void callAddMessage(ModelAndView modelAndView, Exception exception) {
		SimpleMessageExceptionResolver resolver = BeanUtils.instantiateClass(SimpleMessageExceptionResolver.class);
		Method method = BeanUtils.findMethod(SimpleMessageExceptionResolver.class, "addMessages", new Class[] {
				ModelAndView.class, Exception.class });
		method.setAccessible(true);
		ReflectionUtils.invokeMethod(method, resolver, new Object[] { modelAndView, exception });
	}
}
