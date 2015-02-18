package org.transgalactica.management.data.people.bo.impl;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.transgalactica.management.data.materiel.bo.impl.JpaVaisseauEntity;
import org.transgalactica.management.data.referentiel.bo.EmployeType;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AbstractJpaEmployeEntity.class)
public abstract class AbstractJpaEmployeEntity_ {

	public static volatile SetAttribute<AbstractJpaEmployeEntity, JpaVaisseauEntity> vaisseaux;
	public static volatile SingularAttribute<AbstractJpaEmployeEntity, Long> matricule;
	public static volatile SingularAttribute<AbstractJpaEmployeEntity, Date> dateEmbauche;
	public static volatile SingularAttribute<AbstractJpaEmployeEntity, EmployeType> type;
	public static volatile SingularAttribute<AbstractJpaEmployeEntity, String> nom;

}

