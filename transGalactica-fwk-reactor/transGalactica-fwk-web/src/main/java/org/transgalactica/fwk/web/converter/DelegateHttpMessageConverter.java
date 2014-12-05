package org.transgalactica.fwk.web.converter;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class DelegateHttpMessageConverter<T> implements HttpMessageConverter<T> {

	private final HttpMessageConverter<T> delegate;

	public DelegateHttpMessageConverter(HttpMessageConverter<T> delegate) {
		this.delegate = delegate;
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return delegate.getSupportedMediaTypes();
	}

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return delegate.canRead(clazz, mediaType);
	}

	@Override
	public T read(Class<? extends T> clazz, HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		return delegate.read(clazz, inputMessage);
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return delegate.canWrite(clazz, mediaType);
	}

	@Override
	public void write(T t, MediaType contentType, HttpOutputMessage outputMessage) throws IOException,
			HttpMessageNotWritableException {
		delegate.write(t, contentType, outputMessage);
	}
}
