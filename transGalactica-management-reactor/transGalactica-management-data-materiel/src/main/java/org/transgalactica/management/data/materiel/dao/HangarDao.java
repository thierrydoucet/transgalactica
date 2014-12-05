package org.transgalactica.management.data.materiel.dao;

import java.util.List;

import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.HangarSummary;

public interface HangarDao {

	HangarEntity findByNumero(Long numero);

	void refresh(HangarEntity hangarEntity);

	void persist(HangarEntity hangarEntity);

	void remove(HangarEntity hangarEntity);

	List<HangarSummary> findAllHangars();

	List<HangarSummary> findHangarsByLocalisation(String name);

	List<HangarSummary> findHangarsWithVaisseauxOfModele(String modele);
}
