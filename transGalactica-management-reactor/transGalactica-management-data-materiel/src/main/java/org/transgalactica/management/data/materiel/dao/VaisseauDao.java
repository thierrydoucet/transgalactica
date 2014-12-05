package org.transgalactica.management.data.materiel.dao;

import java.util.List;

import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;

public interface VaisseauDao {

	VaisseauEntity findByImmatriculation(String immatriculation);

	List<VaisseauSummary> findVaisseauxOfHangarWithLocalisation(String localisation);

	void refresh(VaisseauEntity vaisseauEntity);

	void persist(VaisseauEntity vaisseauEntity);

	void remove(VaisseauEntity vaisseauEntity);

	/**
	 * L'interet de cette requete passe par l'utilisation du polymorphisme et de
	 * la clause left join pour ramener les vaisseau non affecté à un hangar..
	 */
	List<VaisseauSummary> findVaisseauxIntergalactique();

	int countVaisseauOfHangar(HangarEntity hangar);

	int countVaisseauOfHangarsWithLocalisation(String localisation);

	List<VaisseauSummary> findByCriteria(VaisseauSearchCriteria criteres);

	List<VaisseauSummary> findWithoutHangar();
}
