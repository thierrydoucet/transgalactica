package org.transgalactica.fwk.test.clienthttp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;

/**
 * Implementation bouchon de ClientHttpRequest permettant de fournir des flux de
 * données a partir de ressources Spring.
 * 
 * @see ResourceBoundClientHttpResponse
 * @see ResourceBoundClientHttpRequestFactory
 * 
 * @author Thierry
 */
class ResourceBoundClientHttpRequest implements ClientHttpRequest {

	private HttpHeaders headers = new HttpHeaders();

	private HttpMethod method;

	private URI uri;

	private OutputStream body = new ByteArrayOutputStream();

	private ClientHttpResponse response;

	protected ResourceBoundClientHttpRequest() {
	}

	protected ResourceBoundClientHttpRequest(URI uri, HttpMethod method, ClientHttpResponse response) {
		this.uri = uri;
		this.method = method;
		this.response = response;
	}

	@Override
	public ClientHttpResponse execute() throws IOException {
		return response;
	}

	@Override
	public HttpMethod getMethod() {
		return method;
	}

	@Override
	public URI getURI() {
		return uri;
	}

	@Override
	public OutputStream getBody() throws IOException {
		return body;
	}

	@Override
	public HttpHeaders getHeaders() {
		return headers;
	}

	/*
	 * Accesseurs
	 */

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	public ClientHttpResponse getResponse() {
		return response;
	}

	public void setResponse(ClientHttpResponse response) {
		this.response = response;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}
}