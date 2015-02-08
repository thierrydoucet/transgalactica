package org.transgalactica.management.data.referentiel.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.transgalactica.management.data.referentiel.TestConfig;
import org.transgalactica.management.data.referentiel.bo.impl.JpaMecanicienSpecialiteEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@TransactionConfiguration
@Transactional
public class CacheTest {

	@Autowired
	private EntityManagerFactory emf;

	@Test
	public void testCacheMecanicienSpecialiteEntity() {
		Cache l2 = emf.getCache();
		EntityManager em = emf.createEntityManager();
		assertFalse(l2.contains(JpaMecanicienSpecialiteEntity.class, 1L));

		// Mise en cache L2 sur find.
		JpaMecanicienSpecialiteEntity e1 = em.find(JpaMecanicienSpecialiteEntity.class, 1L);

		assertTrue(l2.contains(JpaMecanicienSpecialiteEntity.class, 1L));

		JpaMecanicienSpecialiteEntity e2 = em.find(JpaMecanicienSpecialiteEntity.class, 1L);
		assertSame(e1, e2);

		EntityManager em2 = emf.createEntityManager();
		JpaMecanicienSpecialiteEntity e3 = em2.find(JpaMecanicienSpecialiteEntity.class, 1L);
		assertNotSame(e1, e3); // Pourquoi le L2 ne renvoi pas les même
								// instance?
		assertEquals(e1, e3);
	}
}
