package org.transgalactica.management.business.logistics.service.config;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.transgalactica.fwk.test.util.SecurityContextTestUtils;
import org.transgalactica.management.business.logistics.exception.HangarInexistantException;
import org.transgalactica.management.business.logistics.service.HangarService;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.impl.JpaHangarEntity;
import org.transgalactica.test.AbstractContextTest;

public class ServiceTransactionInterceptorTest extends AbstractContextTest {

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	@Qualifier("daoHangarService")
	private HangarService hangarService;

	private TransactionDefinition transactionDefinition;

	@Before
	public void setSecurityRoleInContext() {
		SecurityContextTestUtils.setUsernamePasswordAuthenticationTokenInSecurityContext("superGestionnaire", "xxx",
				"ROLE_SUPER_GESTIONNAIRE");
	}

	@Before
	public void createTransactionDefinition() {
		transactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	}

	private HangarEntity createHangar() {
		HangarEntity hangar = (HangarEntity) BeanUtils.instantiateClass(JpaHangarEntity.class);
		hangar.setLocalisation("test");
		hangar.setNombreEmplacements(1);
		return hangar;
	}

	@Test
	public void testProxificationServiceHangar() {
		assertTrue(hangarService instanceof Advised);
		Advised advised = (Advised) hangarService;
		Advisor[] advisors = advised.getAdvisors();
		boolean transactionInterceptorFound = false;
		for (int i = 0; i < advisors.length && !transactionInterceptorFound; i++) {
			transactionInterceptorFound = advisors[i].getAdvice() instanceof TransactionInterceptor;
		}
		assertTrue(transactionInterceptorFound);
	}

	@DirtiesContext
	@Test
	public void testCommit() {
		HangarEntity hangar = createHangar();
		TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
		hangarService.enregistrerHangar(hangar);
		assertFalse(status.isCompleted());
		transactionManager.commit(status);
		assertTrue(status.isCompleted());
		hangarService.chargerHangar(hangar.getNumero());
	}

	@Test
	public void testRollback() {
		HangarEntity hangar = createHangar();
		TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
		hangarService.enregistrerHangar(hangar);
		assertFalse(status.isCompleted());
		transactionManager.rollback(status);
		assertTrue(status.isCompleted());
		try {
			hangarService.chargerHangar(hangar.getNumero());
		}
		catch (HangarInexistantException e) {
			return;
		}
		fail();
	}
}
