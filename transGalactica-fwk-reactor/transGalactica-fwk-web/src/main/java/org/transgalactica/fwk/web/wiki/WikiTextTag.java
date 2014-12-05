package org.transgalactica.fwk.web.wiki;

import java.io.IOException;
import java.util.EnumSet;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.TagUtils;

import com.admc.jcreole.JCreole;
import com.admc.jcreole.JCreolePrivilege;

/**
 * Tag use to convert a wiki text as html. The wiki text to convert is in the
 * tag body.
 * 
 * @author Thierry
 */
public class WikiTextTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	private String privileges = "NONE";

	private String var;

	private String scope = TagUtils.SCOPE_PAGE;

	/**
	 * Default public constructor (Tag API).
	 */
	public WikiTextTag() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int doAfterBody() throws JspTagException {
		String wikiText = bodyContent.getString();
		bodyContent.clearBody();

		try {
			String html = convertWikiTextToHtml(new StringBuilder(wikiText));
			// Expose as variable, if demanded, else write to the page.
			if (StringUtils.isNotEmpty(var)) {
				pageContext.setAttribute(var, html, TagUtils.getScope(scope));
			}
			else {
				writeHtml(html);
			}
		}
		catch (IOException e) {
			throw new JspTagException(e.toString(), e);
		}

		return SKIP_BODY;
	}

	/**
	 * Translate the content of the params <code>wikiText</code> (as Creole
	 * language) to HTML.<br>
	 * This method deals with the paser and privileges using the
	 * <code>privileges</code> attributs.
	 */
	protected String convertWikiTextToHtml(StringBuilder wikiText) throws IOException {
		// JCreole not seems to be tread safe
		JCreole jCreole = new JCreole();

		// deal with privileges
		if (StringUtils.contains(privileges, "FULL")) {
			jCreole.setPrivileges(EnumSet.allOf(JCreolePrivilege.class));
		}
		else if (StringUtils.contains(privileges, "NONE")) {
			jCreole.setPrivileges(EnumSet.noneOf(JCreolePrivilege.class));
		}
		else {
			for (String privilege : org.springframework.util.StringUtils.delimitedListToStringArray(privileges, ",")) {
				jCreole.getPrivileges().add(JCreolePrivilege.valueOf(privilege));
			}
		}

		return jCreole.parseCreole(wikiText);
	}

	/**
	 * Write the message to the page.
	 * <p>
	 * Can be overridden in subclasses, e.g. for testing purposes.
	 */
	protected void writeHtml(String html) throws IOException {
		getPreviousOut().write(html);
	}

	/**
	 * Set pribileges what plugin directives may be used by Creole page authors.
	 * <p>
	 * Could be FULL, NONE or any JCreolePrivilege separated by comma.
	 */
	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}

	/**
	 * Set PageContext attribute name under which to expose a variable that
	 * contains the translated HTML.
	 * @see #setScope
	 * @see javax.servlet.jsp.PageContext#setAttribute
	 */
	public void setVar(String var) {
		this.var = var;
	}

	/**
	 * Set the scope to export the variable to. Default is SCOPE_PAGE ("page").
	 * @see #setVar
	 * @see org.springframework.web.util.TagUtils#SCOPE_PAGE
	 * @see javax.servlet.jsp.PageContext#setAttribute
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
}
