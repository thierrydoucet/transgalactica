package org.transgalactica.fwk.test.clienthttp;

import java.io.IOException;
import java.net.URI;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.util.Assert;

/**
 * Implementation bouchon de ClientHttpRequestFactory permettant de fournir des
 * flux de données a partir de ressources Spring.
 * 
 * @see ResourceBoundClientHttpRequest
 * @see ResourceBoundClientHttpRequestFactory
 * 
 * @author Thierry
 */
public class ResourceBoundClientHttpRequestFactory implements ClientHttpRequestFactory, InitializingBean {

	private Logger logger = LoggerFactory.getLogger(ResourceBoundClientHttpRequestFactory.class);

	private Set<ResourceBoundHolder> resources;

	protected ResourceBoundClientHttpRequestFactory() {
	}

	@Override
	public void afterPropertiesSet() {
		for (ResourceBoundHolder resource : resources) {
			logger.info("Bind '{}' on  '{}' / '{}' / '{}'", resource.getUri(), resource.getContentType(),
					ArrayUtils.toString(resource.getMethods()), resource.getResource());
		}
	}

	@Override
	public ClientHttpRequest createRequest(final URI uri, final HttpMethod method) throws IOException {
		Assert.notNull(uri, "No uri provided");
		String uriAsText = uri.toString();
		logger.info("Handling uri '{}'", uriAsText);

		ResourceBoundHolder resource = findResource(uriAsText, method);
		Assert.isTrue(resource != null, "Can't find resource for '" + method + "' / '" + uriAsText + "'");

		ResourceBoundClientHttpResponse response = new ResourceBoundClientHttpResponse(resource.getContentType(),
				resource.getStatus(), resource.getHeaders(), resource.getResource());
		return new ResourceBoundClientHttpRequest(uri, method, response);
	}

	private ResourceBoundHolder findResource(String uriAsText, HttpMethod method) {
		for (ResourceBoundHolder resource : resources) {
			if (uriAsText.equals(resource.getUri()) && ArrayUtils.contains(resource.getMethods(), method)) {
				return resource;
			}
		}
		return null;
	}

	/*
	 * Accesseurs
	 */

	public Set<ResourceBoundHolder> getResources() {
		return resources;
	}

	public void setResources(Set<ResourceBoundHolder> resources) {
		this.resources = resources;
	}
}
