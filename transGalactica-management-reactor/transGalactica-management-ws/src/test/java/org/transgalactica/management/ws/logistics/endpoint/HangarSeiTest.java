package org.transgalactica.management.ws.logistics.endpoint;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.validation.ValidationException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.util.ReflectionTestUtils;
import org.transgalactica.fwk.remote.exception.RemoteBusinessException;
import org.transgalactica.fwk.test.util.SecurityContextTestUtils;
import org.transgalactica.fwk.validation.exception.BusinessException;
import org.transgalactica.management.business.logistics.service.HangarService;
import org.transgalactica.management.ws.AbstractWebTest;
import org.transgalactica.management.ws.logistics.data.BasicHangarDto;

public class HangarSeiTest extends AbstractWebTest {

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
	@DirtiesContext
	public void testAppelServiceErreurMetier() throws RemoteBusinessException {
		HangarService hangarService = mock(HangarService.class);
		when(hangarService.rechercherHangars(any())).thenThrow(new BusinessException("Test bigbadaboum!"));
		ReflectionTestUtils.setField(sei, "hangarService", hangarService);

		sei.rechercherHangars("et paf");
	}

	@Test(expected = ValidationException.class)
	public void testAppelServiceErreurValidation() throws RemoteBusinessException {
		sei.rechercherHangars("  ");
	}
}
