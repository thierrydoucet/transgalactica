package org.transgalactica.fwk.domain.bean;

import java.lang.reflect.Method;

import org.springframework.util.ReflectionUtils.MethodFilter;

/**
 * Filtre de methodes ({@link MethodFilter}) s'appliquant sur la recherche des
 * annotations {@link BusinessIdentifier}.
 * 
 * @author Thierry
 */
public class BusinessIdentifierMethodFilter implements MethodFilter {

	public BusinessIdentifierMethodFilter() {
	}

	@Override
	public boolean matches(Method method) {
		return method.getAnnotation(BusinessIdentifier.class) != null;
	}
}