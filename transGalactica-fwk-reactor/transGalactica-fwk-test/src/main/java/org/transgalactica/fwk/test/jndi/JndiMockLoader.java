package org.transgalactica.fwk.test.jndi;

import java.util.Map;
import java.util.Map.Entry;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

public class JndiMockLoader implements InitializingBean, DisposableBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(JndiMockLoader.class);

	private Map<String, ?> jndiObjects;

	private SimpleNamingContextBuilder builder;

	public JndiMockLoader() {
	}

	@Override
	public void afterPropertiesSet() throws NamingException {
		builder = getOrCreateNamingContextBuilder();
		builder.deactivate();
		bindJndiObjects();
		builder.activate();
	}

	private SimpleNamingContextBuilder getOrCreateNamingContextBuilder() {
		SimpleNamingContextBuilder b = SimpleNamingContextBuilder.getCurrentContextBuilder();
		if (b == null) {
			LOGGER.info("No SimpleNamingContextBuilder found, creating a new one.");
			b = new SimpleNamingContextBuilder();
		}
		return b;
	}

	private void bindJndiObjects() {
		for (Entry<String, ?> jndiObject : getJndiObjects().entrySet()) {
			LOGGER.info("Binding JndiObject : {}", jndiObject);
			builder.bind(jndiObject.getKey(), jndiObject.getValue());
		}
	}

	@Override
	public void destroy() {
		builder.clear();
	}

	/*
	 * Accesseurs
	 */

	public Map<String, ?> getJndiObjects() {
		return jndiObjects;
	}

	public void setJndiObjects(Map<String, ?> jndiObjects) {
		this.jndiObjects = jndiObjects;
	}
}
