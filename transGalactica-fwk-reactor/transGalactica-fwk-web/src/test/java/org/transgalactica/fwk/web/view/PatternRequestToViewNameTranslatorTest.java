package org.transgalactica.fwk.web.view;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.mock.web.MockHttpServletRequest;
import org.transgalactica.fwk.web.view.PatternRequestToViewNameTranslator;

public class PatternRequestToViewNameTranslatorTest {

	private PatternRequestToViewNameTranslator viewNameTranslator;

	public PatternRequestToViewNameTranslatorTest() {
		this.viewNameTranslator = BeanUtils.instantiateClass(PatternRequestToViewNameTranslator.class);
		Map<String, String> patterns = new HashMap<>();
		patterns.put("test1/*", "test1");
		patterns.put("*/test2", "test2");
		patterns.put("*/test3/*", "test3");
		this.viewNameTranslator.setPatterns(patterns);
	}

	@Test
	public void testMatch_test1() {
		HttpServletRequest request = new MockHttpServletRequest("GET", "/test1/toto");
		assertEquals("test1", viewNameTranslator.getViewName(request));
	}

	@Test
	public void testMatch_test2() {
		HttpServletRequest request = new MockHttpServletRequest("GET", "/toto/test2");
		assertEquals("test2", viewNameTranslator.getViewName(request));
	}

	@Test
	public void testMatch_test3() {
		HttpServletRequest request = new MockHttpServletRequest("GET", "toto/test3/titi");
		assertEquals("test3", viewNameTranslator.getViewName(request));
	}

	@Test
	public void testMatch_default() {
		HttpServletRequest request = new MockHttpServletRequest("GET", "/toto");
		assertEquals("toto", viewNameTranslator.getViewName(request));
	}
}
