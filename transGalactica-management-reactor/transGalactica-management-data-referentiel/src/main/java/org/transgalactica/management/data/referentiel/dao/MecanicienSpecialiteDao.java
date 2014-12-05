package org.transgalactica.management.data.referentiel.dao;

import java.util.List;

import org.transgalactica.management.data.referentiel.bo.MecanicienSpecialiteEntity;

public interface MecanicienSpecialiteDao {

	List<MecanicienSpecialiteEntity> findAll();

	MecanicienSpecialiteEntity findByNomSpecialite(String nom);
}
