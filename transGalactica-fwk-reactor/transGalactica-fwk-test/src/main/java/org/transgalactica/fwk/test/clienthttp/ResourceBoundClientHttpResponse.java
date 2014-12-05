package org.transgalactica.fwk.test.clienthttp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

/**
 * Implementation bouchon de ClientHttpResponse permettant de fournir des flux
 * de données a partir de ressources Spring.
 * 
 * @see ResourceBoundClientHttpRequest
 * @see ResourceBoundClientHttpRequestFactory
 * 
 * @author Thierry
 */
public class ResourceBoundClientHttpResponse implements ClientHttpResponse {

	private HttpStatus status;

	private Resource resource;

	private final HttpHeaders headers = new HttpHeaders();

	protected ResourceBoundClientHttpResponse() {

	}

	protected ResourceBoundClientHttpResponse(String contentType, HttpStatus status, Map<String, String> httpHeaders,
			Resource resource) {
		headers.add("Content-Type", contentType);
		for (Map.Entry<String, String> httpHeader : httpHeaders.entrySet()) {
			headers.add(httpHeader.getKey(), httpHeader.getValue());
		}
		this.resource = resource;
		this.status = status;
	}

	@Override
	public InputStream getBody() throws IOException {
		return resource.getInputStream();
	}

	@Override
	public HttpHeaders getHeaders() {
		return headers;
	}

	@Override
	public HttpStatus getStatusCode() throws IOException {
		return this.status;
	}

	@Override
	public int getRawStatusCode() throws IOException {
		return this.status.value();
	}

	@Override
	public String getStatusText() throws IOException {
		return this.status.getReasonPhrase();
	}

	@Override
	public void close() {
		// NOP
	}

	/*
	 * Accesseurs
	 */

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public String getContentType() {
		return headers.getContentType().toString();
	}

	public void setContentType(String contentType) {
		headers.set("Content-Type", contentType);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}