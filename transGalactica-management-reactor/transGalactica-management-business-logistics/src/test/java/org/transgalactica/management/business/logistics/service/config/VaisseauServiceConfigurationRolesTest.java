package org.transgalactica.management.business.logistics.service.config;

import static org.transgalactica.management.business.logistics.service.mock.MockFactory.mockVaisseauEntity;
import static org.transgalactica.management.business.logistics.service.mock.MockFactory.mockVaisseauSearchCriteria;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.transgalactica.fwk.test.util.SecurityContextTestUtils;
import org.transgalactica.management.business.logistics.TestConfig;
import org.transgalactica.management.business.logistics.service.VaisseauService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class VaisseauServiceConfigurationRolesTest {

	@Autowired
	@Qualifier("noOperationVaisseauService")
	private VaisseauService serviceVaisseau;

	private void setGestionnaireInContext() {
		SecurityContextTestUtils.setUsernamePasswordAuthenticationTokenInSecurityContext("gestionnaire", "xxx",
				"ROLE_GESTIONNAIRE");
	}

	private void setSuperGestionnaireInContext() {
		SecurityContextTestUtils.setUsernamePasswordAuthenticationTokenInSecurityContext("superGestionnaire", "xxx",
				"ROLE_SUPER_GESTIONNAIRE");
	}

	@Test
	public void testRechercherVaisseaux_RoleSuperGestionnaire() {
		setSuperGestionnaireInContext();
		serviceVaisseau.rechercherVaisseaux(mockVaisseauSearchCriteria());
	}

	@Test
	public void testRechercherVaisseaux_RoleGestionnaire() {
		setGestionnaireInContext();
		serviceVaisseau.rechercherVaisseaux(mockVaisseauSearchCriteria());
	}

	@Test(expected = AccessDeniedException.class)
	public void testChargerVaisseau_RoleAnomyme() {
		SecurityContextTestUtils.setAnomymeInContext();
		serviceVaisseau.chargerVaisseau("mock");
	}

	@Test
	public void testChargerVaisseau_RoleSuperGestionnaire() {
		setSuperGestionnaireInContext();
		serviceVaisseau.chargerVaisseau("mock");
	}

	@Test
	public void testChargerVaisseau_RoleGestionnaire() {
		setGestionnaireInContext();
		serviceVaisseau.chargerVaisseau("mock");
	}

	@Test(expected = AccessDeniedException.class)
	public void testEnregistrerVaisseau_RoleAnomyme() {
		SecurityContextTestUtils.setAnomymeInContext();
		serviceVaisseau.enregistrerVaisseau(mockVaisseauEntity());
	}

	@Test
	public void testEnregistrerVaisseau_RoleSuperGestionnaire() {
		setSuperGestionnaireInContext();
		serviceVaisseau.enregistrerVaisseau(mockVaisseauEntity());
	}

	@Test(expected = AccessDeniedException.class)
	public void testEnregistrerVaisseau_RoleGestionnaire() {
		setGestionnaireInContext();
		serviceVaisseau.enregistrerVaisseau(mockVaisseauEntity());
	}

	@Test(expected = AccessDeniedException.class)
	public void testSupprimerVaisseau_RoleAnomyme() {
		SecurityContextTestUtils.setAnomymeInContext();
		serviceVaisseau.supprimerVaisseau(mockVaisseauEntity());
	}

	@Test
	public void testSupprimerVaisseau_RoleSuperGestionnaire() {
		setSuperGestionnaireInContext();
		serviceVaisseau.supprimerVaisseau(mockVaisseauEntity());
	}

	@Test(expected = AccessDeniedException.class)
	public void testSupprimerVaisseau_RoleGestionnaire() {
		setGestionnaireInContext();
		serviceVaisseau.supprimerVaisseau(mockVaisseauEntity());
	}
}
