package org.transgalactica.fwk.domain.bean.factory;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:org/transgalactica/fwk/domain/bean/factory/SpringContextDozerFactory.spring.xml" })
public class SpringContextDozerFactoryTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private SpringContextDozerFactory boFactory;

	@Test
	public void testCreateBoInContext() {
		IDummyBo boTest = (IDummyBo) boFactory.createBean(null, null, IDummyBo.class.getName());
		assertNotNull(boTest);
	}

	@Test(expected = NoSuchBeanDefinitionException.class)
	public void testCreateBoNotInContext() {
		boFactory.createBean(null, null, String.class.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateBoNotExist() {
		boFactory.createBean(null, null, "notExist");
	}

	@Test
	public void testCreateBeanInContext_byId() {
		IDummyTo toTest = (IDummyTo) boFactory.createBean(null, null, IDummyTo.class.getName());
		assertNotNull(toTest);
	}

	@Test
	public void testCreateBeanInContext_byClass() {
		IDummyTo toTest = (IDummyTo) boFactory.createBean(null, null, DummyTo.class.getName());
		assertNotNull(toTest);
	}
}
