package org.transgalactica.web;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.transgalactica.web.accueil.controller.WelcomeController;
import org.transgalactica.web.hangar.controller.HangarDetailController;
import org.transgalactica.web.hangar.controller.HangarListeController;
import org.transgalactica.web.hangar.mapper.HangarMapper;
import org.transgalactica.web.hangar.model.HangarCommand;
import org.transgalactica.web.vaisseau.mapper.VaisseauMapper;
import org.transgalactica.web.vaisseau.model.VaisseauCommand;

public class ChargementContextTest extends AbstractWebTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testInitialisationControllers() {
		assertNotNull(applicationContext.getBean(WelcomeController.class));

		assertNotNull(applicationContext.getBean(HangarDetailController.class));
		assertNotNull(applicationContext.getBean(HangarListeController.class));

		assertNotNull(applicationContext
				.getBean(org.transgalactica.web.vaisseau.controller.VaisseauListeController.class));
		assertNotNull(applicationContext
				.getBean(org.transgalactica.web.vaisseau.controller.VaisseauDetailController.class));
	}

	@Test
	public void testInitialisationModels() {
		assertNotNull(applicationContext.getBean(HangarCommand.class));
		assertNotNull(applicationContext.getBean(VaisseauCommand.class));
	}

	@Test
	public void testInitialisationMappers() {
		assertNotNull(applicationContext.getBean(HangarMapper.class));
		assertNotNull(applicationContext.getBean(VaisseauMapper.class));
	}

	@Test
	public void testInitialisationRules() {
		// no rules yet
	}
}
