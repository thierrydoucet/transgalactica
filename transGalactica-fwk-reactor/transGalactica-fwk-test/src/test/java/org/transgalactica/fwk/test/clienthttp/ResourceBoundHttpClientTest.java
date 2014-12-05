package org.transgalactica.fwk.test.clienthttp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:org/transgalactica/fwk/test/clienthttp/ResourceBoundHttpClientTest.spring.xml" })
public class ResourceBoundHttpClientTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private ResourceBoundClientHttpRequestFactory factory;

	@Test(expected = IllegalArgumentException.class)
	public void testCreationMockInexistant() throws IOException {
		factory.createRequest(URI.create("http://toto/titi"), null);
	}

	@Test
	public void testCreationMockExistant() throws IOException {
		ClientHttpRequest request = factory.createRequest(URI.create("http://montest/unitaire"), HttpMethod.GET);
		assertNotNull(request);
		assertEquals(HttpMethod.GET, request.getMethod());
		assertEquals("http://montest/unitaire", request.getURI().toString());
		assertNotNull(request.getHeaders());

		ClientHttpResponse response = request.execute();
		assertNotNull(response);
		assertEquals("OK", response.getStatusText());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("application/xml", response.getHeaders().getContentType().toString());
		assertEquals("TestResource.xml", ((ResourceBoundClientHttpResponse) response).getResource().getFilename());
		assertNotNull(response.getBody());
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()));
		assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", reader.readLine());
		assertEquals("<foo>", reader.readLine());
		assertEquals("	<bar />", reader.readLine());
		assertEquals("</foo>", reader.readLine());
	}
}
