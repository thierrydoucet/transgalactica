package org.transgalactica.management.business.logistics.service.config;

import static org.transgalactica.management.business.logistics.service.mock.MockFactory.mockVaisseauEntity;
import static org.transgalactica.management.business.logistics.service.mock.MockFactory.mockVaisseauSearchCriteria;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.transgalactica.fwk.test.util.SecurityContextTestUtils;
import org.transgalactica.management.business.logistics.service.VaisseauService;
import org.transgalactica.test.AbstractContextTest;

public class VaisseauServiceConfigurationRolesTest extends AbstractContextTest {

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
	public void testRechercherVaisseauxRoleAnomyme() {
		SecurityContextTestUtils.setAnomymeInContext();
		serviceVaisseau.rechercherVaisseaux(mockVaisseauSearchCriteria());
	}

	@Test
	public void testRechercherVaisseauxRoleSuperGestionnaire() {
		setSuperGestionnaireInContext();
		serviceVaisseau.rechercherVaisseaux(mockVaisseauSearchCriteria());
	}

	@Test
	public void testRechercherVaisseauxRoleGestionnaire() {
		setGestionnaireInContext();
		serviceVaisseau.rechercherVaisseaux(mockVaisseauSearchCriteria());
	}

	@Test(expected = AccessDeniedException.class)
	public void testChargerVaisseauRoleAnomyme() {
		SecurityContextTestUtils.setAnomymeInContext();
		serviceVaisseau.chargerVaisseau("mock");
	}

	@Test
	public void testChargerVaisseauRoleSuperGestionnaire() {
		setSuperGestionnaireInContext();
		serviceVaisseau.chargerVaisseau("mock");
	}

	@Test
	public void testChargerVaisseauRoleGestionnaire() {
		setGestionnaireInContext();
		serviceVaisseau.chargerVaisseau("mock");
	}

	@Test(expected = AccessDeniedException.class)
	public void testEnregistrerVaisseauRoleAnomyme() {
		SecurityContextTestUtils.setAnomymeInContext();
		serviceVaisseau.enregistrerVaisseau(mockVaisseauEntity());
	}

	@Test
	public void testEnregistrerVaisseauRoleSuperGestionnaire() {
		setSuperGestionnaireInContext();
		serviceVaisseau.enregistrerVaisseau(mockVaisseauEntity());
	}

	@Test(expected = AccessDeniedException.class)
	public void testEnregistrerVaisseauRoleGestionnaire() {
		setGestionnaireInContext();
		serviceVaisseau.enregistrerVaisseau(mockVaisseauEntity());
	}

	@Test(expected = AccessDeniedException.class)
	public void testSupprimerVaisseauRoleAnomyme() {
		SecurityContextTestUtils.setAnomymeInContext();
		serviceVaisseau.supprimerVaisseau(mockVaisseauEntity());
	}

	@Test
	public void testSupprimerVaisseauRoleSuperGestionnaire() {
		setSuperGestionnaireInContext();
		serviceVaisseau.supprimerVaisseau(mockVaisseauEntity());
	}

	@Test(expected = AccessDeniedException.class)
	public void testSupprimerVaisseauRoleGestionnaire() {
		setGestionnaireInContext();
		serviceVaisseau.supprimerVaisseau(mockVaisseauEntity());
	}
}
