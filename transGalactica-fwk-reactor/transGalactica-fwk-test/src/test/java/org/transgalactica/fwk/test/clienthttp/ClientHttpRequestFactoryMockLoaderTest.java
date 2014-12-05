package org.transgalactica.fwk.test.clienthttp;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(locations = { "classpath:org/transgalactica/fwk/test/clienthttp/ResourceBoundHttpClientTest.spring.xml" })
public class ClientHttpRequestFactoryMockLoaderTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void testActivationBouchon() {
		ClientHttpRequestFactory mock = applicationContext.getBean("resourceBoundClientHttpRequestFactory-1",
				ClientHttpRequestFactory.class);

		RestTemplate restTemplate1 = applicationContext.getBean("RestTemplate-1", RestTemplate.class);
		assertSame(mock, restTemplate1.getRequestFactory());

		RestTemplate restTemplate2 = applicationContext.getBean("RestTemplate-2", RestTemplate.class);
		assertNotSame(mock, restTemplate2.getRequestFactory());

		RestTemplate restTemplate3 = applicationContext.getBean("RestTemplate-3", RestTemplate.class);
		assertSame(mock, restTemplate3.getRequestFactory());
	}
}
