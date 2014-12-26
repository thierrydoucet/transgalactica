package org.transgalactica.swing.test;

import org.junit.Before;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.transgalactica.management.data.rest.dao.HangarDao;
import org.transgalactica.management.data.rest.dao.VaisseauDao;
import org.transgalactica.test.AbstractContextTest;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public abstract class AbstractMockedSpringContextTest extends AbstractContextTest {

	@Before
	public void mockHangarDao() {
		RestTemplate template = (RestTemplate) ReflectionTestUtils.getField(
				applicationContext.getBean(HangarDao.class), "restTemplate");
		template.setRequestFactory(applicationContext.getBean("mockClientHttpRequestFactory",
				ClientHttpRequestFactory.class));
	}

	@Before
	public void mockVaisseauDao() {
		RestTemplate template = (RestTemplate) ReflectionTestUtils.getField(
				applicationContext.getBean(VaisseauDao.class), "restTemplate");
		template.setRequestFactory(applicationContext.getBean("mockClientHttpRequestFactory",
				ClientHttpRequestFactory.class));
	}
}
