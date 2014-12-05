package org.transgalactica.fwk.domain.bean;

import java.lang.reflect.Field;

import org.springframework.util.ReflectionUtils.FieldFilter;

/**
 * Filtre d'attributs ({@link FieldFilter}) s'appliquant sur la recherche des
 * annotations {@link BusinessIdentifier}.
 * 
 * @author Thierry
 */
public class BusinessIdentifierFieldFilter implements FieldFilter {

	public BusinessIdentifierFieldFilter() {
	}

	@Override
	public boolean matches(Field field) {
		return field.getAnnotation(BusinessIdentifier.class) != null;
	}
}