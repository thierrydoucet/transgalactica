package org.transgalactica.management.data.people.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.people.bo.EmployeSummary;
import org.transgalactica.management.data.people.bo.impl.AbstractJpaEmployeEntity;
import org.transgalactica.management.data.people.dao.impl.EmployeDaoCustom;

@org.springframework.stereotype.Repository
public interface EmployeDao extends Repository<AbstractJpaEmployeEntity, Long>, EmployeDaoCustom {

	EmployeEntity findByMatricule(Long matricule);

	void save(EmployeEntity employeEntity);

	void delete(EmployeEntity employeEntity);

	@Query("select distinct new org.transgalactica.management.data.people.bo.BasicEmployeSummary(e.matricule, e.nom, e.dateEmbauche, e.type)" //
			+ " from AbstractJpaEmployeEntity e inner join e.vaisseaux v" //
			+ " where v.modele = :modele"//
			+ " order by e.matricule")
	List<EmployeSummary> findEmployesByModeleDeVaisseau(@Param("modele") String modele);

	@Query("select distinct new org.transgalactica.management.data.people.bo.BasicEmployeSummary(e.matricule, e.nom, e.dateEmbauche, e.type)" //
			+ " from AbstractJpaEmployeEntity e inner join e.vaisseaux v" //
			+ " where v is not null and v.class=JpaVaisseauIntergalactiqueEntity" //
			+ " order by e.matricule")
	List<EmployeSummary> findEmployesOfVaisseauIntergalactique();
}
