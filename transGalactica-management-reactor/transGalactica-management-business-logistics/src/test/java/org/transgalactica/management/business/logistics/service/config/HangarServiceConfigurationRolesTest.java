package org.transgalactica.management.business.logistics.service.config;

import static org.transgalactica.management.business.logistics.service.mock.MockFactory.mockHangarEntity;
import static org.transgalactica.management.business.logistics.service.mock.MockFactory.mockHangarSearchCriteria;
import static org.transgalactica.management.business.logistics.service.mock.MockFactory.mockVaisseauEntity;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.transgalactica.fwk.test.util.SecurityContextTestUtils;
import org.transgalactica.management.business.logistics.service.HangarService;
import org.transgalactica.test.AbstractContextTest;

public class HangarServiceConfigurationRolesTest extends AbstractContextTest {

	@Autowired
	@Qualifier("noOperationHangarService")
	private HangarService serviceHangar;

	private void setGestionnaireInContext() {
		SecurityContextTestUtils.setUsernamePasswordAuthenticationTokenInSecurityContext("gestionnaire", "xxx",
				"ROLE_GESTIONNAIRE");
	}

	private void setSuperGestionnaireInContext() {
		SecurityContextTestUtils.setUsernamePasswordAuthenticationTokenInSecurityContext("superGestionnaire", "xxx",
				"ROLE_SUPER_GESTIONNAIRE");
	}

	@Test
	public void testRechercherHangarsRoleAnomyme() {
		SecurityContextTestUtils.setAnomymeInContext();
		this.serviceHangar.rechercherHangars(mockHangarSearchCriteria());
	}

	@Test
	public void testRechercherHangarsRoleSuperGestionnaire() {
		setSuperGestionnaireInContext();
		this.serviceHangar.rechercherHangars(mockHangarSearchCriteria());
	}

	@Test
	public void testRechercherHangarsRoleGestionnaire() {
		setGestionnaireInContext();
		this.serviceHangar.rechercherHangars(mockHangarSearchCriteria());
	}

	@Test(expected = AccessDeniedException.class)
	public void testChargerHangarRoleAnomyme() {
		SecurityContextTestUtils.setAnomymeInContext();
		this.serviceHangar.chargerHangar(null);
	}

	@Test
	public void testChargerHangarRoleSuperGestionnaire() {
		setSuperGestionnaireInContext();
		this.serviceHangar.chargerHangar(null);
	}

	@Test
	public void testChargerHangarRoleGestionnaire() {
		setGestionnaireInContext();
		this.serviceHangar.chargerHangar(null);
	}

	@Test(expected = AccessDeniedException.class)
	public void testEnregistrerHangarRoleAnomyme() {
		SecurityContextTestUtils.setAnomymeInContext();
		this.serviceHangar.enregistrerHangar(mockHangarEntity());
	}

	@Test
	public void testEnregistrerHangarRoleSuperGestionnaire() {
		setSuperGestionnaireInContext();
		this.serviceHangar.enregistrerHangar(mockHangarEntity());
	}

	@Test(expected = AccessDeniedException.class)
	public void testEnregistrerHangarRoleGestionnaire() {
		setGestionnaireInContext();
		this.serviceHangar.enregistrerHangar(mockHangarEntity());
	}

	@Test(expected = AccessDeniedException.class)
	public void testSupprimerHangarRoleAnomyme() {
		SecurityContextTestUtils.setAnomymeInContext();
		this.serviceHangar.supprimerHangar(mockHangarEntity());
	}

	@Test
	public void testSupprimerHangarRoleSuperGestionnaire() {
		setSuperGestionnaireInContext();
		this.serviceHangar.supprimerHangar(mockHangarEntity());
	}

	@Test(expected = AccessDeniedException.class)
	public void testSupprimerHangarRoleGestionnaire() {
		setGestionnaireInContext();
		this.serviceHangar.supprimerHangar(mockHangarEntity());
	}

	@Test(expected = AccessDeniedException.class)
	public void testAffecterVaisseauAuHangarRoleAnomyme() {
		SecurityContextTestUtils.setAnomymeInContext();
		this.serviceHangar.affecterVaisseauAuHangar(mockVaisseauEntity(), mockHangarEntity());
	}

	@Test
	public void testAffecterVaisseauAuHangarRoleSuperGestionnaire() {
		setSuperGestionnaireInContext();
		this.serviceHangar.affecterVaisseauAuHangar(mockVaisseauEntity(), mockHangarEntity());
	}

	public void testAffecterVaisseauAuHangarRoleGestionnaire() {
		setGestionnaireInContext();
		this.serviceHangar.affecterVaisseauAuHangar(mockVaisseauEntity(), mockHangarEntity());
	}

}
