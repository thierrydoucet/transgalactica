package org.transgalactica.management.data.people.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.apache.commons.lang3.StringUtils;
import org.transgalactica.management.data.materiel.bo.impl.JpaVaisseauEntity;
import org.transgalactica.management.data.people.bo.BasicEmployeSummary;
import org.transgalactica.management.data.people.bo.EmployeSearchCriteria;
import org.transgalactica.management.data.people.bo.EmployeSummary;
import org.transgalactica.management.data.people.bo.impl.AbstractJpaEmployeEntity;

public class EmployeDaoImpl implements EmployeDaoCustom {

	@PersistenceContext
	private EntityManager em;

	protected EmployeDaoImpl() {
	}

	// TODO https://jira.spring.io/browse/DATAJPA-51
	// TODO https://jira.spring.io/browse/DATACMNS-89
	@Override
	public List<EmployeSummary> findEmployesByCriteria(EmployeSearchCriteria critereRechercheEmploye) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<EmployeSummary> query = cb.createQuery(EmployeSummary.class);

		Root<AbstractJpaEmployeEntity> from = query.from(AbstractJpaEmployeEntity.class);

		List<Predicate> conditions = new ArrayList<>();
		if (StringUtils.isNotBlank(critereRechercheEmploye.getNomEmploye())) {
			conditions.add(cb.like(cb.upper(from.<String> get("nom")), critereRechercheEmploye.getNomEmploye()
					.toUpperCase()));
		}
		if (critereRechercheEmploye.getDateEmbaucheEmployeDebut() != null) {
			conditions.add(cb.greaterThanOrEqualTo(from.<Date> get("dateEmbauche"),
					critereRechercheEmploye.getDateEmbaucheEmployeDebut()));

		}
		if (critereRechercheEmploye.getDateEmbaucheEmployeFin() != null) {
			conditions.add(cb.lessThanOrEqualTo(from.<Date> get("dateEmbauche"),
					critereRechercheEmploye.getDateEmbaucheEmployeFin()));
		}
		if (StringUtils.isNotBlank(critereRechercheEmploye.getImmatriculationVaisseau())) {
			SetJoin<AbstractJpaEmployeEntity, JpaVaisseauEntity> join = from.joinSet("vaisseaux");
			conditions.add(cb.equal(join.get("immatriculation"), critereRechercheEmploye.getImmatriculationVaisseau()));
		}

		query.where(conditions.toArray(new Predicate[conditions.size()]));
		query.orderBy(cb.asc(from.get("nom")));
		query.select(cb.construct(BasicEmployeSummary.class, from.get("matricule"), from.get("nom"),
				from.get("dateEmbauche"), from.get("type")));

		return em.createQuery(query).getResultList();
	}
}
