package org.transgalactica.management.data.rest.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.transgalactica.management.data.rest.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class HrReferentielDaoTest {

	@Inject
	@Named("mockClientHttpRequestFactory")
	private ClientHttpRequestFactory mock;

	@Inject
	private HrReferentielDao dao;

	@Before
	public void mockDao() {
		RestTemplate template = (RestTemplate) ReflectionTestUtils.getField(dao, "restTemplate");
		template.setRequestFactory(mock);
	}

	@Test
	public void testGetAllEmployeType() {
		List<String> types = dao.getAllEmployeType();

		assertEquals(2, types.size());
		assertEquals("PILOTE", types.get(0));
	}

	@Test
	public void testGetAllMecanicienspecialite() {
		List<String> types = dao.getAllMecanicienspecialite();

		assertEquals(5, types.size());
		assertEquals("Armement", types.get(0));
	}
}