package org.transgalactica.management.business.logistics.service.config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor;
import org.transgalactica.management.business.logistics.service.HangarService;
import org.transgalactica.test.AbstractContextTest;

public class ServiceMethodSecurityInterceptorTest extends AbstractContextTest {

	@Test
	public void testProxificationServiceHangar() {
		Object service = applicationContext.getBean("daoHangarService", HangarService.class);
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
