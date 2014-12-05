package org.transgalactica.fwk.web.wiki;

import static org.junit.Assert.assertEquals;

import javax.servlet.jsp.JspTagException;

import org.junit.Test;
import org.springframework.mock.web.MockBodyContent;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPageContext;
import org.transgalactica.fwk.web.wiki.WikiTextTag;

public class WikiTextTagTest {

	@Test
	public void testConvertWikiTextToHtml() throws JspTagException {
		WikiTextTag tag = new WikiTextTag();

		// mock
		MockPageContext pageContext = new MockPageContext();
		tag.setPageContext(pageContext);
		tag.setVar("var");
		MockBodyContent bodyContent = new MockBodyContent("Mon TU wiki", new MockHttpServletResponse());
		tag.setBodyContent(bodyContent);

		// test
		tag.doAfterBody();

		// assert
		assertEquals("<p>Mon TU wiki</p>\n", pageContext.getAttribute("var"));
	}
}
