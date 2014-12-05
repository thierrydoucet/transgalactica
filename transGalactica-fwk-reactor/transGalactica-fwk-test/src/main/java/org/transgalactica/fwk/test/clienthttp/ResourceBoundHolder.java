package org.transgalactica.fwk.test.clienthttp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

public class ResourceBoundHolder {

	private String uri;

	private HttpStatus status = HttpStatus.OK;

	private Resource resource = new ByteArrayResource(new byte[0]);

	private String contentType;

	private HttpMethod[] methods = new HttpMethod[] { HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT,
			HttpMethod.DELETE };

	private Map<String, String> headers = new HashMap<String, String>();

	public ResourceBoundHolder() {
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public HttpMethod[] getMethods() {
		return Arrays.copyOf(methods, methods.length);
	}

	public void setMethods(HttpMethod[] httpMethods) {
		Assert.notNull(httpMethods);
		this.methods = Arrays.copyOf(httpMethods, methods.length);
	}

	public void setMethod(HttpMethod httpMethod) {
		this.methods = new HttpMethod[] { httpMethod };
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		ResourceBoundHolder rhs = (ResourceBoundHolder) obj;
		return new EqualsBuilder().append(uri, rhs.uri).append(methods, rhs.methods).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(uri).append(methods).toHashCode();
	}
}
