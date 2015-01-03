package org.transgalactica.management.data.materiel.bo;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Ce BO utilise une cle metier, en plus de la cle technique.
 * 
 * BK immatriculation
 * @author Thierry
 */
public interface VaisseauEntity extends Serializable {

	@NotBlank
	@Size(max = 50)
	String getModele();

	void setModele(String modele);

	@NotBlank
	@Size(max = 50)
	String getImmatriculation();

	void setImmatriculation(String immatriculation);

	short getNombreDePassagers();

	void setNombreDePassagers(short nombreDePassagers);

	long getCapaciteDeFret();

	void setCapaciteDeFret(long capaciteDeFret);

	int getVitesse();

	void setVitesse(int vitesse);

	int getAutonomie();

	void setAutonomie(int autonomie);

	HangarEntity getHangar();

	void setHangar(HangarEntity hangar);
}
