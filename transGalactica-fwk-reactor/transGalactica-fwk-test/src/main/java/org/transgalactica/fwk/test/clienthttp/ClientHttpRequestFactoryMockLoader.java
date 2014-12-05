package org.transgalactica.fwk.test.clienthttp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.support.HttpAccessor;

public class ClientHttpRequestFactoryMockLoader implements InitializingBean, ApplicationContextAware {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientHttpRequestFactoryMockLoader.class);

	private ApplicationContext applicationContext;

	private boolean ignoreUnresolvableAccessors = true;

	private ClientHttpRequestFactory mock;

	private List<String> accessorsNamesToMock;

	protected ClientHttpRequestFactoryMockLoader() {
	}

	@Override
	public void afterPropertiesSet() {
		if (org.springframework.util.CollectionUtils.isEmpty(accessorsNamesToMock)) {
			LOGGER.warn("No accessors to mock have been defined");
			return;
		}
		for (String idAccessor : accessorsNamesToMock) {
			mockAccessor(idAccessor);
		}
	}

	private void mockAccessor(String accessorName) {
		if (applicationContext.containsBean(accessorName)) {
			HttpAccessor accessor = applicationContext.getBean(accessorName, HttpAccessor.class);
			accessor.setRequestFactory(mock);
		}
		else if (ignoreUnresolvableAccessors) {
			LOGGER.warn("Accessors with name {} not found in context", accessorName);
		}
		else {
			throw new IllegalArgumentException("Can't find accessor with name " + accessorName);
		}
	}

	/*
	 * Accesseurs
	 */

	public ClientHttpRequestFactory getMock() {
		return mock;
	}

	public void setMock(ClientHttpRequestFactory mock) {
		this.mock = mock;
	}

	public List<String> getAccessorsNamesToMock() {
		return accessorsNamesToMock;
	}

	public void setAccessorsNamesToMock(List<String> accessorsNamesToMock) {
		this.accessorsNamesToMock = accessorsNamesToMock;
	}

	public boolean isIgnoreUnresolvableAccessors() {
		return ignoreUnresolvableAccessors;
	}

	public void setIgnoreUnresolvableAccessors(boolean ignoreUnresolvableAccessors) {
		this.ignoreUnresolvableAccessors = ignoreUnresolvableAccessors;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
