package org.transgalactica.management.rest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.transgalactica.management.rest.logistics.data.HangarCommand;
import org.transgalactica.management.rest.logistics.data.HangarDetailDto;
import org.transgalactica.management.rest.logistics.data.HangarDto;
import org.transgalactica.management.rest.logistics.data.HangarDtos;
import org.transgalactica.management.rest.logistics.data.VaisseauCommand;
import org.transgalactica.management.rest.logistics.data.VaisseauDetailDto;
import org.transgalactica.management.rest.logistics.data.VaisseauDto;
import org.transgalactica.management.rest.logistics.data.VaisseauDtos;
import org.transgalactica.management.rest.logistics.mapper.impl.DozerHangarMapper;
import org.transgalactica.management.rest.logistics.mapper.impl.DozerVaisseauMapper;
import org.transgalactica.management.rest.logistics.restservice.impl.SpringMVCHangarRestService;
import org.transgalactica.management.rest.logistics.restservice.impl.SpringMVCVaisseauRestService;

public class ChargementContextTest extends AbstractSpringContextTest {

	@Test
	public void testInitialisationContext() {
		assertNotNull(applicationContext);
	}

	@Test
	public void testInitialisationControllers() {
		assertNotNull(applicationContext.getBean(SpringMVCHangarRestService.class));
		assertNotNull(applicationContext.getBean(SpringMVCVaisseauRestService.class));
	}

	@Test
	public void testInitialisationModel() {
		assertNotNull(applicationContext.getBean(HangarCommand.class));
		assertNotNull(applicationContext.getBean(HangarDtos.class));
		assertNotNull(applicationContext.getBean(HangarDto.class.getName()));
		assertNotNull(applicationContext.getBean(HangarDetailDto.class));
		assertNotNull(applicationContext.getBean(HangarDetailDto.VaisseauDto.class));

		assertNotNull(applicationContext.getBean(VaisseauCommand.class));
		assertNotNull(applicationContext.getBean(VaisseauDtos.class));
		assertNotNull(applicationContext.getBean(VaisseauDto.class.getName()));
		assertNotNull(applicationContext.getBean(VaisseauDetailDto.class));

	}

	@Test
	public void testInitialisationMappers() {
		assertNotNull(applicationContext.getBean(DozerHangarMapper.class));
		assertNotNull(applicationContext.getBean(DozerVaisseauMapper.class));
	}

	@Test
	public void testInitialisationRules() {
		// no rules yet
	}
}
