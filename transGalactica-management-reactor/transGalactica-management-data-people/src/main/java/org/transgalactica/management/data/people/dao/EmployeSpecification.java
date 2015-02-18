package org.transgalactica.management.data.people.dao;

import static org.springframework.util.Assert.notNull;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.people.bo.impl.AbstractJpaEmployeEntity;
import org.transgalactica.management.data.people.bo.impl.AbstractJpaEmployeEntity_;
import org.transgalactica.management.data.referentiel.bo.EmployeType;

public abstract class EmployeSpecification {

	public static Specification<AbstractJpaEmployeEntity> nomContient(final String valeur) {
		return (root, query, cb) -> {
			return cb.like(root.get(AbstractJpaEmployeEntity_.nom), "%" + valeur + "%");
		};
	}

	public static Specification<AbstractJpaEmployeEntity> estMecanicien() {
		return (root, query, cb) -> {
			return cb.equal(root.get(AbstractJpaEmployeEntity_.type), EmployeType.MECANICIEN);
		};
	}

	public static Specification<AbstractJpaEmployeEntity> estEmbaucheApresLe(final Date debut) {
		return (root, query, cb) -> {
			return cb.greaterThanOrEqualTo(root.get(AbstractJpaEmployeEntity_.dateEmbauche), debut);
		};
	}

	public static Specification<AbstractJpaEmployeEntity> affecteA(final VaisseauEntity vaisseau) {
		notNull(vaisseau, "vaisseau could not be null");
		return (root, query, cb) -> {
			return cb.equal(root.join(AbstractJpaEmployeEntity_.vaisseaux).get("immatriculation"),
					vaisseau.getImmatriculation());
		};
	}
}
