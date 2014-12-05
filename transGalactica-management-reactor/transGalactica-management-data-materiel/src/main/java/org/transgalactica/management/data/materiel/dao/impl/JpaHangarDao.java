package org.transgalactica.management.data.materiel.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.transgalactica.management.data.materiel.bo.BasicHangarSummary;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.data.materiel.bo.impl.JpaHangarEntity;
import org.transgalactica.management.data.materiel.dao.HangarDao;

@Repository
public class JpaHangarDao implements HangarDao {

	@PersistenceContext
	private EntityManager em;

	protected JpaHangarDao() {
	}

	@Override
	public HangarEntity findByNumero(Long identifier) {
		return em.find(JpaHangarEntity.class, identifier);
	}

	@Override
	public void refresh(HangarEntity hangarEntity) {
		em.refresh(hangarEntity);
	}

	@Override
	public void persist(HangarEntity hangarEntity) {
		em.persist(hangarEntity);
	}

	@Override
	public void remove(HangarEntity hangarEntity) {
		em.remove(hangarEntity);
	}

	@Override
	public List<HangarSummary> findAllHangars() {
		return em.createQuery(
				"select new " + BasicHangarSummary.class.getName()
						+ "(h.numero, h.localisation, h.nombreEmplacements) from " + JpaHangarEntity.class.getName()
						+ " h order by h.numero", HangarSummary.class)//
				.getResultList();
	}

	@Override
	public List<HangarSummary> findHangarsByLocalisation(String localisation) {
		return em
				.createQuery(
						"select distinct new " + BasicHangarSummary.class.getName()
								+ "(h.numero, h.localisation, h.nombreEmplacements) from "
								+ JpaHangarEntity.class.getName()
								+ " h where h.localisation like :localisation order by h.numero", HangarSummary.class)//
				.setParameter("localisation", localisation)//
				.getResultList();
	}

	@Override
	public List<HangarSummary> findHangarsWithVaisseauxOfModele(String modele) {
		return em
				.createQuery(
						"select distinct new " + BasicHangarSummary.class.getName()
								+ "(h.numero, h.localisation, h.nombreEmplacements) from "
								+ JpaHangarEntity.class.getName()
								+ " h inner join h.vaisseaux v where v.modele like :modele order by h.numero",
						HangarSummary.class) //
				.setParameter("modele", modele) //
				.getResultList();
	}
}
