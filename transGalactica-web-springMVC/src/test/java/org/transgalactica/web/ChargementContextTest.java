package org.transgalactica.web;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.transgalactica.web.accueil.controller.impl.WelcomeController;
import org.transgalactica.web.hangar.controller.impl.HangarDetailController;
import org.transgalactica.web.hangar.controller.impl.HangarListeController;
import org.transgalactica.web.hangar.mapper.HangarMapper;
import org.transgalactica.web.hangar.model.impl.HangarCommand;
import org.transgalactica.web.vaisseau.mapper.VaisseauMapper;
import org.transgalactica.web.vaisseau.model.impl.VaisseauCommand;

public class ChargementContextTest extends AbstractWebContextTest {

	@Test
	public void testInitialisationControllers() {
		assertNotNull(applicationContext.getBean(WelcomeController.class));

		assertNotNull(applicationContext.getBean(HangarDetailController.class));
		assertNotNull(applicationContext.getBean(HangarListeController.class));

		assertNotNull(applicationContext
				.getBean(org.transgalactica.web.vaisseau.controller.impl.VaisseauListeController.class));
		assertNotNull(applicationContext
				.getBean(org.transgalactica.web.vaisseau.controller.impl.VaisseauDetailController.class));
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
