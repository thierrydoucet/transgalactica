package org.transgalactica.management.data.referentiel.bo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		assertFalse(em.getEntityManagerFactory().getCache().contains(JpaMecanicienSpecialiteEntity.class, 1L));

		// Mise en cache L2 sur find.
		em.find(JpaMecanicienSpecialiteEntity.class, 1L);

		assertTrue(em.getEntityManagerFactory().getCache().contains(JpaMecanicienSpecialiteEntity.class, 1L));
	}
}
