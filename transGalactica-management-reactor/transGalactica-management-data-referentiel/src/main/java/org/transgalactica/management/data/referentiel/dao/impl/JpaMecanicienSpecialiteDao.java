package org.transgalactica.management.data.referentiel.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.transgalactica.management.data.referentiel.bo.MecanicienSpecialiteEntity;
import org.transgalactica.management.data.referentiel.bo.impl.JpaMecanicienSpecialiteEntity;
import org.transgalactica.management.data.referentiel.dao.MecanicienSpecialiteDao;

@Repository
public class JpaMecanicienSpecialiteDao implements MecanicienSpecialiteDao {

	@PersistenceContext
	private EntityManager em;

	protected JpaMecanicienSpecialiteDao() {
	}

	@Override
	public List<MecanicienSpecialiteEntity> findAll() {
		return em.createQuery("from " + JpaMecanicienSpecialiteEntity.class.getName() + " s order by s.nomSpecialite",
				MecanicienSpecialiteEntity.class).getResultList();
	}

	@Override
	public MecanicienSpecialiteEntity findByNomSpecialite(String nom) {
		TypedQuery<MecanicienSpecialiteEntity> query = em.createQuery("select distinct s from "
				+ JpaMecanicienSpecialiteEntity.class.getName() + " s where s.nomSpecialite = :nom",
				MecanicienSpecialiteEntity.class);
		query.setParameter("nom", nom);
		return DataAccessUtils.uniqueResult(query.getResultList());
	}
}
