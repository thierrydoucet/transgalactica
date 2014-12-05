package org.transgalactica.fwk.web.jstl;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public abstract class PropertyUtilTag {

	protected PropertyUtilTag() {
	}

	public static boolean hasProperty(Object o, String propertyName) {
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(o);
		return bw.isReadableProperty(propertyName);
	}
}
