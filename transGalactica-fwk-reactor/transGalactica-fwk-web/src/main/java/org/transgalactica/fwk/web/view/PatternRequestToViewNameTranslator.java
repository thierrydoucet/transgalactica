package org.transgalactica.fwk.web.view;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator;

/**
 * 
 * *\/toto/* + /toto/* + /toto/1 = /toto/ /* +
 * 
 * 
 * 
 */
public class PatternRequestToViewNameTranslator extends DefaultRequestToViewNameTranslator {

	private Logger logger = LoggerFactory.getLogger(PatternRequestToViewNameTranslator.class);

	private AntPathMatcher matcher = new AntPathMatcher();

	private Map<String, String> patterns;

	public PatternRequestToViewNameTranslator() {
	}

	@Override
	protected String transformPath(String lookupPath) {
		String path = super.transformPath(lookupPath);
		for (Entry<String, String> pattern : patterns.entrySet()) {
			if (matcher.match(pattern.getKey(), path)) {
				logger.debug("Match pattern '{}', returning path '{}'", pattern.getKey(), pattern.getValue());
				return pattern.getValue();
			}
		}
		logger.debug("No pattern match returning DefaultRequestToViewNameTranslator path '{}'", path);
		return path;
	}

	public Map<String, String> getPatterns() {
		return Collections.unmodifiableMap(patterns);
	}

	public void setPatterns(Map<String, String> patterns) {
		this.patterns = new LinkedHashMap<>(patterns);
	}
}
