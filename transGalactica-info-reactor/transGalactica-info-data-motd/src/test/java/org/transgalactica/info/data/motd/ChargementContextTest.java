package org.transgalactica.info.data.motd;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.transgalactica.info.data.motd.bo.ImageTo;
import org.transgalactica.info.data.motd.bo.MessageTo;
import org.transgalactica.info.data.motd.mapper.MotdMapper;
import org.transgalactica.info.data.motd.repository.MotdRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContext.class)
public class ChargementContextTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testInitialisationContext() {
		assertNotNull(applicationContext);
	}

	@Test
	public void testInitialisationBos() {
		assertNotNull(applicationContext.getBean(MessageTo.class));
		assertNotNull(applicationContext.getBean(ImageTo.class));
	}

	@Test
	public void testInitialisationDaos() {
		assertNotNull(applicationContext.getBean(MotdRepository.class));
	}

	@Test
	public void testInitialisationMappers() {
		assertNotNull(applicationContext.getBean(MotdMapper.class));
	}
}
