package org.transgalactica.management.data.referentiel.bo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.transgalactica.management.data.referentiel.bo.impl.JpaMecanicienSpecialiteEntity;
import org.transgalactica.test.AbstractTransactionalTest;

public class CacheTest extends AbstractTransactionalTest {

	@PersistenceContext
	private EntityManager em;

	@Test
	public void testCacheMecanicienSpecialiteEntity() {
		Cache l2 = em.getEntityManagerFactory().getCache();
		assertFalse(l2.contains(JpaMecanicienSpecialiteEntity.class, 1L));

		// Mise en cache L2 sur find.
		em.find(JpaMecanicienSpecialiteEntity.class, 1L);

		assertTrue(l2.contains(JpaMecanicienSpecialiteEntity.class, 1L));
	}
}
