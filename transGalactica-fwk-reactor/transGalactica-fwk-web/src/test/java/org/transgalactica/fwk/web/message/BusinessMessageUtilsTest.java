package org.transgalactica.fwk.web.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.transgalactica.fwk.validation.MultipleErrors;
import org.transgalactica.fwk.validation.exception.BusinessException;
import org.transgalactica.fwk.validation.exception.MultipleErrorsException;
import org.transgalactica.fwk.web.message.BusinessMessageUtils;

public class BusinessMessageUtilsTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testAddBusinessMessageCode() {
		Model model = BusinessMessageUtils.addBusinessMessage("code");
		assertNotNull(model);
		assertNotNull(model.asMap().get(BusinessMessageUtils.BUSINESS_MESSAGES_ATTRIBUTE));
		List<MessageSourceResolvable> messages = (List<MessageSourceResolvable>) model.asMap().get(
				BusinessMessageUtils.BUSINESS_MESSAGES_ATTRIBUTE);
		assertEquals(1, messages.size());
		assertEquals("code", messages.get(0).getCodes()[0]);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddBusinessMessageModelMapCode() {
		Model model = new ExtendedModelMap();
		BusinessMessageUtils.addBusinessMessage(model, "code");
		assertNotNull(model.asMap().get(BusinessMessageUtils.BUSINESS_MESSAGES_ATTRIBUTE));
		List<MessageSourceResolvable> messages = (List<MessageSourceResolvable>) model.asMap().get(
				BusinessMessageUtils.BUSINESS_MESSAGES_ATTRIBUTE);
		assertEquals(1, messages.size());
		assertEquals("code", messages.get(0).getCodes()[0]);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddBusinessMessageMessageSourceResolvable() {
		Model model = BusinessMessageUtils.addBusinessMessage(new DefaultMessageSourceResolvable("code"));
		assertNotNull(model);
		assertNotNull(model.asMap().get(BusinessMessageUtils.BUSINESS_MESSAGES_ATTRIBUTE));
		List<MessageSourceResolvable> messages = (List<MessageSourceResolvable>) model.asMap().get(
				BusinessMessageUtils.BUSINESS_MESSAGES_ATTRIBUTE);
		assertEquals(1, messages.size());
		assertEquals("code", messages.get(0).getCodes()[0]);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddBusinessMessageModelMapMessageSourceResolvable() {
		Model model = new ExtendedModelMap();
		BusinessMessageUtils.addBusinessMessage(model, new DefaultMessageSourceResolvable("code"));
		assertNotNull(model.asMap().get(BusinessMessageUtils.BUSINESS_MESSAGES_ATTRIBUTE));
		List<MessageSourceResolvable> messages = (List<MessageSourceResolvable>) model.asMap().get(
				BusinessMessageUtils.BUSINESS_MESSAGES_ATTRIBUTE);
		assertEquals(1, messages.size());
		assertEquals("code", messages.get(0).getCodes()[0]);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddBusinessErrorMessageCode() {
		Model model = BusinessMessageUtils.addBusinessErrorMessage("code");
		assertNotNull(model);
		assertNotNull(model.asMap().get(BusinessMessageUtils.BUSINESS_ERRORS_MESSAGES_ATTRIBUTE));
		List<MessageSourceResolvable> messages = (List<MessageSourceResolvable>) model.asMap().get(
				BusinessMessageUtils.BUSINESS_ERRORS_MESSAGES_ATTRIBUTE);
		assertEquals(1, messages.size());
		assertEquals("code", messages.get(0).getCodes()[0]);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddBusinessErrorMessageModelMapCode() {
		Model model = new ExtendedModelMap();
		BusinessMessageUtils.addBusinessErrorMessage(model, "code");
		assertNotNull(model.asMap().get(BusinessMessageUtils.BUSINESS_ERRORS_MESSAGES_ATTRIBUTE));
		List<MessageSourceResolvable> messages = (List<MessageSourceResolvable>) model.asMap().get(
				BusinessMessageUtils.BUSINESS_ERRORS_MESSAGES_ATTRIBUTE);
		assertEquals(1, messages.size());
		assertEquals("code", messages.get(0).getCodes()[0]);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddBusinessErrorMessageMessageSourceResolvable() {
		Model model = BusinessMessageUtils.addBusinessErrorMessage(new DefaultMessageSourceResolvable("code"));
		assertNotNull(model);
		assertNotNull(model.asMap().get(BusinessMessageUtils.BUSINESS_ERRORS_MESSAGES_ATTRIBUTE));
		List<MessageSourceResolvable> messages = (List<MessageSourceResolvable>) model.asMap().get(
				BusinessMessageUtils.BUSINESS_ERRORS_MESSAGES_ATTRIBUTE);
		assertEquals(1, messages.size());
		assertEquals("code", messages.get(0).getCodes()[0]);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddBusinessErrorMessageModelMapMessageSourceResolvable() {
		Model model = new ExtendedModelMap();
		BusinessMessageUtils.addBusinessErrorMessage(model, new DefaultMessageSourceResolvable("code"));
		assertNotNull(model.asMap().get(BusinessMessageUtils.BUSINESS_ERRORS_MESSAGES_ATTRIBUTE));
		List<MessageSourceResolvable> messages = (List<MessageSourceResolvable>) model.asMap().get(
				BusinessMessageUtils.BUSINESS_ERRORS_MESSAGES_ATTRIBUTE);
		assertEquals(1, messages.size());
		assertEquals("code", messages.get(0).getCodes()[0]);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddBusinessExceptionBusinessException() {
		Model model = BusinessMessageUtils.addBusinessException(new BusinessException("code"));
		assertNotNull(model);
		assertNotNull(model.asMap().get(BusinessMessageUtils.BUSINESS_ERRORS_MESSAGES_ATTRIBUTE));
		List<MessageSourceResolvable> messages = (List<MessageSourceResolvable>) model.asMap().get(
				BusinessMessageUtils.BUSINESS_ERRORS_MESSAGES_ATTRIBUTE);
		assertEquals(1, messages.size());
		assertEquals("code", ((MessageSourceResolvable) messages.get(0).getArguments()[0]).getCodes()[0]);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddBusinessExceptionModelMapBusinessException() {
		Model model = new ExtendedModelMap();
		BusinessMessageUtils.addBusinessException(model, new BusinessException("code"));
		assertNotNull(model.asMap().get(BusinessMessageUtils.BUSINESS_ERRORS_MESSAGES_ATTRIBUTE));
		List<MessageSourceResolvable> messages = (List<MessageSourceResolvable>) model.asMap().get(
				BusinessMessageUtils.BUSINESS_ERRORS_MESSAGES_ATTRIBUTE);
		assertEquals(1, messages.size());
		assertEquals("code", ((MessageSourceResolvable) messages.get(0).getArguments()[0]).getCodes()[0]);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddBusinessExceptionModelMapMultipleErrorsException() {
		Model model = new ExtendedModelMap();
		MultipleErrors errors = new MultipleErrors();
		errors.add("message1");
		errors.add("message2");
		BusinessMessageUtils.addBusinessException(model, new MultipleErrorsException(errors.getAllErrorMessages()));
		assertNotNull(model.asMap().get(BusinessMessageUtils.BUSINESS_ERRORS_MESSAGES_ATTRIBUTE));
		List<MessageSourceResolvable> messages = (List<MessageSourceResolvable>) model.asMap().get(
				BusinessMessageUtils.BUSINESS_ERRORS_MESSAGES_ATTRIBUTE);
		assertEquals(2, messages.size());
		assertEquals("message1", messages.get(0).getCodes()[0]);
		assertEquals("message2", messages.get(1).getCodes()[0]);
	}
}
