package org.transgalactica.management.ws.context;

import static org.junit.Assert.assertNotNull;

import org.dozer.Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.transgalactica.management.ws.AbstractWebTest;
import org.transgalactica.management.ws.logistics.data.BasicHangarDto;
import org.transgalactica.management.ws.logistics.endpoint.HangarSei;
import org.transgalactica.management.ws.logistics.mapper.HangarMapper;

public class ChargementContextTest extends AbstractWebTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testInitialisationContext() {
		assertNotNull(applicationContext);
	}

	@Test
	public void testInitialisationDozer() {
		assertNotNull(applicationContext.getBean(Mapper.class));
	}

	@Test
	public void testInitialisationDatas() {
		assertNotNull(applicationContext.getBean(BasicHangarDto.class));
	}

	@Test
	public void testInitialisationMappers() {
		assertNotNull(applicationContext.getBean(HangarMapper.class));
	}

	@Test
	public void testInitialisationServiceEndpoints() {
		assertNotNull(applicationContext.getBean(HangarSei.class));
	}
}
