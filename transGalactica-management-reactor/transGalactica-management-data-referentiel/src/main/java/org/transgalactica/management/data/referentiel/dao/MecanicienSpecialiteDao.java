package org.transgalactica.management.data.referentiel.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;
import org.transgalactica.management.data.referentiel.bo.MecanicienSpecialiteEntity;
import org.transgalactica.management.data.referentiel.bo.impl.JpaMecanicienSpecialiteEntity;

public interface MecanicienSpecialiteDao extends Repository<JpaMecanicienSpecialiteEntity, Long> {

	List<MecanicienSpecialiteEntity> findAll(Sort orderBy);

	MecanicienSpecialiteEntity findByNomSpecialite(String nom);
}
