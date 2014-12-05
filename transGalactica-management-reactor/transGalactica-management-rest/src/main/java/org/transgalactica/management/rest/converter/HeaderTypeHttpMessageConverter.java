package org.transgalactica.management.rest.converter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.transgalactica.fwk.web.converter.DelegateHttpMessageConverter;

public class HeaderTypeHttpMessageConverter<T> extends DelegateHttpMessageConverter<T> {

	private static final String HEADER_KEY = "TransGalactica-Content-Type";

	/** Mappings from header value to types */
	private final Map<String, Class<? extends T>> headersMapping = new LinkedHashMap<>();

	public HeaderTypeHttpMessageConverter(HttpMessageConverter<T> delegate) {
		super(delegate);
	}

	@Override
	public void write(T t, MediaType contentType, HttpOutputMessage outputMessage) throws IOException,
			HttpMessageNotWritableException {
		String headerValue = findHeaderMapping(t.getClass());
		if (headerValue != null) {
			outputMessage.getHeaders().add(HEADER_KEY, headerValue);
		}
		super.write(t, contentType, outputMessage);
	}

	@Override
	public T read(Class<? extends T> clazz, HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		String headerType = inputMessage.getHeaders().getFirst(HEADER_KEY);
		Class<? extends T> target = findHeaderMapping(headerType);
		if (target != null) {
			return super.read(target, inputMessage);
		}
		return super.read(clazz, inputMessage);
	}

	public HeaderTypeHttpMessageConverter<T> addMapping(String value, Class<? extends T> type) {
		headersMapping.put(value, type);
		return this;
	}

	protected Class<? extends T> findHeaderMapping(String key) {
		return headersMapping.get(key);
	}

	protected String findHeaderMapping(Class<?> clazz) {
		for (Map.Entry<String, Class<? extends T>> entry : headersMapping.entrySet()) {
			if (entry.getValue().isAssignableFrom(clazz)) {
				return entry.getKey();
			}
		}
		return null;
	}
}
