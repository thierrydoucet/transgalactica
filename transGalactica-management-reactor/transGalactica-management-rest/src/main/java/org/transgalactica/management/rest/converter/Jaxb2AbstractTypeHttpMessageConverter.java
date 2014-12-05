package org.transgalactica.management.rest.converter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Source;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;

public class Jaxb2AbstractTypeHttpMessageConverter extends Jaxb2RootElementHttpMessageConverter {

	/**
	 * Mappings from super types to subtypes
	 */
	private final Map<Class<?>, Class<?>> typesMapping = new HashMap<>();

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return super.canRead(findTypeMapping(clazz), mediaType);
	}

	@Override
	protected Object readFromSource(Class<?> clazz, HttpHeaders headers, Source source) throws IOException {
		return super.readFromSource(findTypeMapping(clazz), headers, source);
	}

	public <T> Jaxb2AbstractTypeHttpMessageConverter addTypeMapping(Class<T> superType, Class<? extends T> subType) {
		if (superType.equals(subType)) {
			throw new IllegalArgumentException("Can not add mapping from class to itself");
		}
		if (!superType.isAssignableFrom(subType)) {
			throw new IllegalArgumentException("Can not add mapping from class " + superType.getName() + " to "
					+ subType.getName() + ", as latter is not a subtype of former");
		}
		typesMapping.put(superType, subType);
		return this;
	}

	protected Class<?> findTypeMapping(Class<?> superType) {
		Class<?> subType = typesMapping.get(superType);
		if (subType != null) {
			return subType;
		}
		return superType;
	}
}
