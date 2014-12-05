package org.transgalactica.fwk.web.message;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.transgalactica.fwk.validation.exception.BusinessException;
import org.transgalactica.fwk.validation.exception.ExceptionUtils;
import org.transgalactica.fwk.validation.exception.MultipleErrorsException;

/**
 * Add message to a Model. Message could be shown in view using
 * <code>&lt;spring:message/&gt;</code> tag.
 * 
 * @see MessageSourceResolvable
 */
public abstract class BusinessMessageUtils {

	public static final String BUSINESS_ERRORS_MESSAGES_ATTRIBUTE = "businessErrors";

	public static final String BUSINESS_MESSAGES_ATTRIBUTE = "businessMessages";

	protected BusinessMessageUtils() {
	}

	public static Model addBusinessMessage(String code) {
		Model model = new ExtendedModelMap();
		addBusinessMessage(model, code);
		return model;
	}

	public static void addBusinessMessage(Model model, String code) {
		addBusinessMessage(model, new DefaultMessageSourceResolvable(code));
	}

	public static Model addBusinessMessage(MessageSourceResolvable message) {
		Model model = new ExtendedModelMap();
		addBusinessMessage(model, message);
		return model;
	}

	public static void addBusinessMessage(Model model, MessageSourceResolvable message) {
		addMessage(BUSINESS_MESSAGES_ATTRIBUTE, model, message);
	}

	public static Model addBusinessException(BusinessException exception) {
		Model model = new ExtendedModelMap();
		addBusinessException(model, exception);
		return model;
	}

	public static void addBusinessException(Model model, BusinessException exception) {
		if (exception instanceof MultipleErrorsException) {
			MultipleErrorsException errorsException = (MultipleErrorsException) exception;
			for (MessageSourceResolvable message : errorsException.getErrors()) {
				addBusinessErrorMessage(model, message);
			}
		}
		else {
			MessageSourceResolvable message = ExceptionUtils.getMessageSourceResolvable(exception);
			addBusinessErrorMessage(model, message);
		}
	}

	public static Model addBusinessErrorMessage(String code) {
		Model model = new ExtendedModelMap();
		addBusinessErrorMessage(model, code);
		return model;
	}

	public static void addBusinessErrorMessage(Model model, String code) {
		addBusinessErrorMessage(model, new DefaultMessageSourceResolvable(code));
	}

	public static Model addBusinessErrorMessage(MessageSourceResolvable message) {
		Model model = new ExtendedModelMap();
		addBusinessErrorMessage(model, message);
		return model;
	}

	public static void addBusinessErrorMessage(Model model, MessageSourceResolvable message) {
		addMessage(BUSINESS_ERRORS_MESSAGES_ATTRIBUTE, model, message);
	}

	protected static void addMessage(String attributeKey, Model model, MessageSourceResolvable message) {
		List<MessageSourceResolvable> messages = (List<MessageSourceResolvable>) model.asMap().get(attributeKey);
		if (messages == null) {
			messages = new ArrayList<MessageSourceResolvable>(2);
			model.addAttribute(attributeKey, messages);
		}
		messages.add(message);
		model.addAttribute(attributeKey, messages);
	}
}
