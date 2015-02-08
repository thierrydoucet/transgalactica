package org.transgalactica.management.business.logistics.service.config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.transgalactica.management.business.logistics.TestConfig;
import org.transgalactica.management.business.logistics.service.HangarService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ServiceMethodSecurityInterceptorTest {

	@Autowired
	private BeanFactory beanFactory;

	@Test
	public void testProxificationServiceHangar() {
		Object service = beanFactory.getBean("daoHangarService", HangarService.class);
		assertNotNull(service);
		assertTrue(service instanceof Advised);
		Advised advised = (Advised) service;
		Advisor[] advisors = advised.getAdvisors();
		boolean methodSecurityInterceptorFound = false;
		for (int i = 0; i < advisors.length && !methodSecurityInterceptorFound; i++) {
			methodSecurityInterceptorFound = advisors[i].getAdvice() instanceof MethodSecurityInterceptor;
		}
		assertTrue(methodSecurityInterceptorFound);
	}
}
