package org.transgalactica.fwk.web.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.transgalactica.fwk.web.converter.DelegateHttpMessageConverter;

public class DelegateHttpMessageConverterTest {

	private MockHttpMessageConverter delegate = new MockHttpMessageConverter();

	private DelegateHttpMessageConverter<String> converter = new DelegateHttpMessageConverter<>(delegate);

	@Before
	public void init() {
		delegate = new MockHttpMessageConverter();
		converter = new DelegateHttpMessageConverter<>(delegate);
	}

	@Test
	public void canRead() {
		assertEquals(delegate.canRead(null, null), converter.canRead(null, null));
	}

	@Test
	public void read() throws HttpMessageNotReadableException, IOException {
		assertEquals(delegate.read(null, null), converter.read(null, null));
	}

	@Test
	public void canWrite() {
		assertEquals(delegate.canWrite(null, null), converter.canWrite(null, null));
	}

	@Test
	public void write() throws HttpMessageNotWritableException, IOException {
		converter.write(null, null, null);
		assertTrue(delegate.writeCalled);
	}

	private class MockHttpMessageConverter implements HttpMessageConverter<String> {

		private boolean writeCalled = false;

		@Override
		public boolean canRead(Class<?> clazz, MediaType mediaType) {
			return true;
		}

		@Override
		public boolean canWrite(Class<?> clazz, MediaType mediaType) {
			return true;
		}

		@Override
		public List<MediaType> getSupportedMediaTypes() {
			return Collections.singletonList(MediaType.ALL);
		}

		@Override
		public String read(Class<? extends String> clazz, HttpInputMessage inputMessage) throws IOException,
				HttpMessageNotReadableException {
			return "value";
		}

		@Override
		public void write(String t, MediaType contentType, HttpOutputMessage outputMessage) throws IOException,
				HttpMessageNotWritableException {
			writeCalled = true;
		}
	}
}
