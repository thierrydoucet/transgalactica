package org.transgalactica.management.data.materiel.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.transgalactica.management.data.materiel.bo.BasicVaisseauSummary;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;
import org.transgalactica.management.data.materiel.bo.impl.JpaVaisseauEntity;
import org.transgalactica.management.data.materiel.bo.impl.JpaVaisseauIntergalactiqueEntity;
import org.transgalactica.management.data.materiel.dao.VaisseauDao;

@Repository
public class JpaVaisseauDao implements VaisseauDao {

	@PersistenceContext
	private EntityManager em;

	protected JpaVaisseauDao() {
	}

	@Override
	public void refresh(VaisseauEntity vaisseauEntity) {
		em.refresh(vaisseauEntity);
	}

	@Override
	public void persist(VaisseauEntity vaisseauEntity) {
		em.persist(vaisseauEntity);
	}

	@Override
	public void remove(VaisseauEntity vaisseauEntity) {
		em.remove(vaisseauEntity);
	}

	@Override
	public VaisseauEntity findByImmatriculation(String immatriculation) {
		TypedQuery<VaisseauEntity> query = em.createQuery("from " + JpaVaisseauEntity.class.getName()
				+ " v where v.immatriculation = :immatriculation", VaisseauEntity.class);
		query.setParameter("immatriculation", immatriculation);
		return DataAccessUtils.uniqueResult(query.getResultList());
	}

	@Override
	public List<VaisseauSummary> findVaisseauxOfHangarWithLocalisation(String localisation) {
		return em
				.createQuery(
						"select new " + BasicVaisseauSummary.class.getName()
								+ "(v.immatriculation, v.modele, v.hangar.localisation) from "
								+ JpaVaisseauEntity.class.getName()
								+ " v where v.hangar.localisation like :localisation order by v.immatriculation",
						VaisseauSummary.class)//
				.setParameter("localisation", localisation)//
				.getResultList();
	}

	@Override
	public List<VaisseauSummary> findVaisseauxIntergalactique() {
		return em.createQuery(
				"select new " + BasicVaisseauSummary.class.getName()
						+ "(v.immatriculation, v.modele, h.localisation) from "
						+ JpaVaisseauIntergalactiqueEntity.class.getName()
						+ " v left join v.hangar h order by v.immatriculation", VaisseauSummary.class)//
				.getResultList();
	}

	@Override
	public int countVaisseauOfHangar(HangarEntity hangar) {
		TypedQuery<Number> query = em.createQuery("select count(*) from " + JpaVaisseauEntity.class.getName()
				+ " v where v.hangar.id = :id", Number.class);
		query.setParameter("id", hangar.getNumero());
		return query.getSingleResult().intValue();
	}

	@Override
	public int countVaisseauOfHangarsWithLocalisation(String localisation) {
		TypedQuery<Number> query = em.createQuery("select count(*) from " + JpaVaisseauEntity.class.getName()
				+ " v where v.hangar.localisation like :localisation group by v.hangar.localisation", Number.class);
		query.setParameter("localisation", localisation);
		return query.getSingleResult().intValue();
	}

	@Override
	public List<VaisseauSummary> findByCriteria(VaisseauSearchCriteria criteres) {
		return em
				.createQuery(
						"select new "
								+ BasicVaisseauSummary.class.getName()
								+ "(v.immatriculation, v.modele, h.localisation) from "
								+ (criteres.isIntergalactique() ? JpaVaisseauIntergalactiqueEntity.class
										: JpaVaisseauEntity.class).getName()
								+ " v left join v.hangar h where v.immatriculation like :immatriculation and v.modele like :modele order by v.immatriculation",
						VaisseauSummary.class)//
				.setParameter("immatriculation",
						StringUtils.isNotBlank(criteres.getImmatriculation()) ? criteres.getImmatriculation() : "%") //
				.setParameter("modele", StringUtils.isNotBlank(criteres.getModele()) ? criteres.getModele() : "%") //
				.getResultList();
	}

	@Override
	public List<VaisseauSummary> findWithoutHangar() {
		return em.createQuery(
				"select new " + BasicVaisseauSummary.class.getName() + "(v.immatriculation, v.modele) from "
						+ JpaVaisseauEntity.class.getName() + " v where v.hangar is null", VaisseauSummary.class) //
				.getResultList();
	}
}
