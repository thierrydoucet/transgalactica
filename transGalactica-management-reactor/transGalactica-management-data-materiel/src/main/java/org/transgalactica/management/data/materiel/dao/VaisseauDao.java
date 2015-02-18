package org.transgalactica.management.data.materiel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;
import org.transgalactica.management.data.materiel.bo.impl.JpaVaisseauEntity;
import org.transgalactica.management.data.materiel.dao.impl.VaisseauDaoCustom;

public interface VaisseauDao extends Repository<JpaVaisseauEntity, Long>, VaisseauDaoCustom,
		QueryDslPredicateExecutor<VaisseauEntity> {

	VaisseauEntity findByImmatriculation(String immatriculation);

	void save(VaisseauEntity vaisseauEntity);

	void delete(VaisseauEntity vaisseauEntity);

	@Query("select new org.transgalactica.management.data.materiel.bo.BasicVaisseauSummary(v.immatriculation, v.modele, v.hangar.localisation) " //
			+ " from JpaVaisseauEntity v" //
			+ " where v.hangar.localisation like :localisation" //
			+ " order by v.immatriculation")
	List<VaisseauSummary> findByHangarLocalisationOrderByImmatriculation(@Param("localisation") String localisation);

	/**
	 * L'interet de cette requete passe par l'utilisation du polymorphisme et de
	 * la clause left join pour ramener les vaisseau non affecté à un hangar.
	 */
	@Query("select new org.transgalactica.management.data.materiel.bo.BasicVaisseauSummary(v.immatriculation, v.modele, h.localisation) " //
			+ " from JpaVaisseauIntergalactiqueEntity v left join v.hangar h" //
			+ " order by v.immatriculation")
	List<VaisseauSummary> findVaisseauxIntergalactique();

	/**
	 * select count(*) from JpaVaisseauEntity v where v.hangar = :hangar
	 */
	int countByHangar(HangarEntity hangar);

	/**
	 * select count(*) from JpaVaisseauEntity v where v.hangar.localisation like
	 * :localisation group by v.hangar.localisation
	 */
	int countByHangarLocalisation(String localisation);

	@Query("select new org.transgalactica.management.data.materiel.bo.BasicVaisseauSummary(v.immatriculation, v.modele) " //
			+ " from JpaVaisseauEntity v" //
			+ " where v.hangar is null" //
			+ " order by v.immatriculation")
	List<VaisseauSummary> findByHangarIsNullOrderByImmatriculation();
}