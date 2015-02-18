package org.transgalactica.management.data.materiel.dao.impl;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.transgalactica.management.data.materiel.bo.BasicVaisseauSummary;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;
import org.transgalactica.management.data.materiel.bo.impl.JpaVaisseauEntity;
import org.transgalactica.management.data.materiel.bo.impl.JpaVaisseauIntergalactiqueEntity;

public class VaisseauDaoImpl implements VaisseauDaoCustom {

	@PersistenceContext
	private EntityManager em;

	protected VaisseauDaoImpl() {
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
				.setParameter("immatriculation", defaultIfBlank(criteres.getImmatriculation(), "%")) //
				.setParameter("modele", defaultIfBlank(criteres.getModele(), "%")) //
				.getResultList();
	}

}
