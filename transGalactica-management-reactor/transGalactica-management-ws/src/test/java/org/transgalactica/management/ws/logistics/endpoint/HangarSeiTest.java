package org.transgalactica.management.ws.logistics.endpoint;

import static org.junit.Assert.assertEquals;

import javax.validation.ValidationException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.fwk.remote.exception.RemoteBusinessException;
import org.transgalactica.fwk.test.util.SecurityContextTestUtils;
import org.transgalactica.management.ws.AbstractSpringContextTest;
import org.transgalactica.management.ws.logistics.data.BasicHangarDto;
import org.transgalactica.management.ws.logistics.endpoint.HangarSei;

public class HangarSeiTest extends AbstractSpringContextTest {

	@Autowired
	private HangarSei sei;

	@Before
	public void setAnomymeInContext() {
		SecurityContextTestUtils.setUsernamePasswordAuthenticationTokenInSecurityContext("usename", "password",
				"ROLE_SUPER_GESTIONNAIRE");
	}

	@Test
	public void testAppelService() throws RemoteBusinessException {
		BasicHangarDto[] hangars = sei.rechercherHangars("Arakis");
		assertEquals(1, hangars.length);

		BasicHangarDto hangar = hangars[0];
		assertEquals(new Long(2), hangar.getNumero());
		assertEquals("Arakis", hangar.getLocalisation());
		assertEquals(100, hangar.getNombreEmplacements());
	}

	@Test(expected = RemoteBusinessException.class)
	@Ignore("Non actuellement testable : pas de methode envoyer d'exception metier")
	public void testAppelServiceErreurMetier() throws RemoteBusinessException {
		// TODO Non actuellement testable : pas de methode envoyer d'exception
		// metier
	}

	@Test(expected = ValidationException.class)
	public void testAppelServiceErreurValidation() throws RemoteBusinessException {
		sei.rechercherHangars("  ");
	}
}
