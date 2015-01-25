package org.transgalactica.management.data.materiel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.data.materiel.bo.impl.JpaHangarEntity;

public interface HangarDao extends Repository<JpaHangarEntity, Long> {

	HangarEntity findByNumero(Long numero);

	void save(HangarEntity hangarEntity);

	void delete(HangarEntity hangarEntity);

	@Query("select new org.transgalactica.management.data.materiel.bo.BasicHangarSummary(h.numero, h.localisation, h.nombreEmplacements)" //
			+ " from JpaHangarEntity h" //
			+ " order by h.numero")
	List<HangarSummary> findAllOrderByNumero();

	@Query("select distinct new org.transgalactica.management.data.materiel.bo.BasicHangarSummary(h.numero, h.localisation, h.nombreEmplacements)" //
			+ " from JpaHangarEntity h" //
			+ " where h.localisation like ?1" //
			+ " order by h.numero")
	List<HangarSummary> findByLocalisationOrderByNumero(String localisation);

	@Query("select distinct new org.transgalactica.management.data.materiel.bo.BasicHangarSummary(h.numero, h.localisation, h.nombreEmplacements)"//
			+ " from JpaHangarEntity h inner join h.vaisseaux v"//
			+ " where v.modele like ?1"//
			+ " order by h.numero")
	List<HangarSummary> findByVaisseauxModeleOrderByNumero(String modele);
}
