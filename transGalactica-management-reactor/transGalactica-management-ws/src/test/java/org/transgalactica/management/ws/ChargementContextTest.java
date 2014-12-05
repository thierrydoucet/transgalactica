package org.transgalactica.management.ws;

import static org.junit.Assert.assertNotNull;

import org.dozer.Mapper;
import org.junit.Test;
import org.transgalactica.management.ws.logistics.data.BasicHangarDto;
import org.transgalactica.management.ws.logistics.endpoint.HangarSei;
import org.transgalactica.management.ws.logistics.mapper.HangarMapper;

public class ChargementContextTest extends AbstractSpringContextTest {

	@Test
	public void testInitialisationContext() {
		assertNotNull(applicationContext);
	}

	@Test
	public void testInitialisationDozer() {
		assertNotNull(applicationContext.getBean(Mapper.class.getName()));
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
