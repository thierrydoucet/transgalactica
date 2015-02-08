package org.transgalactica.management.business.logistics.service.config;

import static org.transgalactica.management.business.logistics.service.mock.MockFactory.mockHangarEntity;
import static org.transgalactica.management.business.logistics.service.mock.MockFactory.mockHangarSearchCriteria;
import static org.transgalactica.management.business.logistics.service.mock.MockFactory.mockVaisseauEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.transgalactica.fwk.test.util.SecurityContextTestUtils;
import org.transgalactica.management.business.logistics.TestConfig;
import org.transgalactica.management.business.logistics.service.HangarService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class HangarServiceConfigurationRolesTest {

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
	public void testRechercherHangars_RoleAnomyme() {
		SecurityContextTestUtils.setAnomymeInContext();
		this.serviceHangar.rechercherHangars(mockHangarSearchCriteria());
	}

	@Test
	public void testRechercherHangars_RoleSuperGestionnaire() {
		setSuperGestionnaireInContext();
		this.serviceHangar.rechercherHangars(mockHangarSearchCriteria());
	}

	@Test
	public void testRechercherHangars_RoleGestionnaire() {
		setGestionnaireInContext();
		this.serviceHangar.rechercherHangars(mockHangarSearchCriteria());
	}

	@Test(expected = AccessDeniedException.class)
	public void testChargerHangar_RoleAnomyme() {
		SecurityContextTestUtils.setAnomymeInContext();
		this.serviceHangar.chargerHangar(null);
	}

	@Test
	public void testChargerHangar_RoleSuperGestionnaire() {
		setSuperGestionnaireInContext();
		this.serviceHangar.chargerHangar(1L);
	}

	@Test
	public void testChargerHangar_RoleGestionnaire() {
		setGestionnaireInContext();
		this.serviceHangar.chargerHangar(1L);
	}

	@Test(expected = AccessDeniedException.class)
	public void testEnregistrerHangar_RoleAnomyme() {
		SecurityContextTestUtils.setAnomymeInContext();
		this.serviceHangar.enregistrerHangar(mockHangarEntity());
	}

	@Test
	public void testEnregistrerHangar_RoleSuperGestionnaire() {
		setSuperGestionnaireInContext();
		this.serviceHangar.enregistrerHangar(mockHangarEntity());
	}

	@Test(expected = AccessDeniedException.class)
	public void testEnregistrerHangar_RoleGestionnaire() {
		setGestionnaireInContext();
		this.serviceHangar.enregistrerHangar(mockHangarEntity());
	}

	@Test(expected = AccessDeniedException.class)
	public void testSupprimerHangar_RoleAnomyme() {
		SecurityContextTestUtils.setAnomymeInContext();
		this.serviceHangar.supprimerHangar(mockHangarEntity());
	}

	@Test
	public void testSupprimerHangar_RoleSuperGestionnaire() {
		setSuperGestionnaireInContext();
		this.serviceHangar.supprimerHangar(mockHangarEntity());
	}

	@Test(expected = AccessDeniedException.class)
	public void testSupprimerHangar_RoleGestionnaire() {
		setGestionnaireInContext();
		this.serviceHangar.supprimerHangar(mockHangarEntity());
	}

	@Test(expected = AccessDeniedException.class)
	public void testAffecterVaisseauAuHangar_RoleAnomyme() {
		SecurityContextTestUtils.setAnomymeInContext();
		this.serviceHangar.affecterVaisseauAuHangar(mockVaisseauEntity(), mockHangarEntity());
	}

	@Test
	public void testAffecterVaisseauAuHangar_RoleSuperGestionnaire() {
		setSuperGestionnaireInContext();
		this.serviceHangar.affecterVaisseauAuHangar(mockVaisseauEntity(), mockHangarEntity());
	}

	public void testAffecterVaisseauAuHangar_RoleGestionnaire() {
		setGestionnaireInContext();
		this.serviceHangar.affecterVaisseauAuHangar(mockVaisseauEntity(), mockHangarEntity());
	}

}
