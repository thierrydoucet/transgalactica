package org.transgalactica.management.data.materiel.dao.impl;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.transgalactica.management.data.materiel.bo.BasicVaisseauSummary;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;
import org.transgalactica.management.data.materiel.bo.impl.QJpaVaisseauEntity;
import org.transgalactica.management.data.materiel.bo.impl.QJpaVaisseauIntergalactiqueEntity;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Projections;
import com.mysema.query.types.expr.BooleanExpression;

public class QueryDSLVaisseauDao implements VaisseauDaoCustom {

	@PersistenceContext
	private EntityManager em;

	protected QueryDSLVaisseauDao() {
	}

	@Override
	public List<VaisseauSummary> findByCriteria(VaisseauSearchCriteria criteres) {
		List<BasicVaisseauSummary> results;
		// .instanceOf / .instanceOfAny ne fonctionnent pas pour JPA.
		if (criteres.isIntergalactique()) {
			QJpaVaisseauIntergalactiqueEntity vaisseau = QJpaVaisseauIntergalactiqueEntity.jpaVaisseauIntergalactiqueEntity;
			BooleanExpression hasImmatriculation = vaisseau.immatriculation.like(defaultIfBlank(
					criteres.getImmatriculation(), "%"));
			BooleanExpression hasModele = vaisseau.modele.like(defaultIfBlank(criteres.getModele(), "%"));
			results = new JPAQuery(em) //
					.from(vaisseau) //
					.leftJoin(vaisseau.hangar) //
					.where(hasImmatriculation.and(hasModele))
					.list(Projections.constructor(BasicVaisseauSummary.class, vaisseau.immatriculation,
							vaisseau.modele, vaisseau.hangar.localisation));
		}
		else {
			QJpaVaisseauEntity vaisseau = QJpaVaisseauEntity.jpaVaisseauEntity;
			BooleanExpression hasImmatriculation = vaisseau.immatriculation.like(defaultIfBlank(
					criteres.getImmatriculation(), "%"));
			BooleanExpression hasModele = vaisseau.modele.like(defaultIfBlank(criteres.getModele(), "%"));
			results = new JPAQuery(em) //
					.from(vaisseau) //
					.leftJoin(vaisseau.hangar) //
					.where(hasImmatriculation.and(hasModele))
					.list(Projections.constructor(BasicVaisseauSummary.class, vaisseau.immatriculation,
							vaisseau.modele, vaisseau.hangar.localisation));
		}
		// TODO transformation de type, voir si on peut faire mieux...
		return new ArrayList<>(results);
	}

}
