package org.transgalactica.management.rest.logistics.data;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public interface VaisseauCommand extends Serializable {

	@NotBlank
	@Size(max = 30)
	String getImmatriculation();

	void setImmatriculation(String immatriculation);

	@NotBlank
	@Size(max = 30)
	String getModele();

	void setModele(String modele);

	@NotNull
	@Min(0)
	short getNombreDePassagers();

	void setNombreDePassagers(short nombreDePassagers);

	@NotNull
	@Min(0)
	long getCapaciteDeFret();

	void setCapaciteDeFret(long capaciteDeFret);

	@NotNull
	@Min(0)
	int getVitesse();

	void setVitesse(int vitesse);

	@NotNull
	@Min(0)
	int getAutonomie();

	void setAutonomie(int autonomie);

	@Min(0)
	Short getMultiplicateurHyperdrive();

	void setMultiplicateurHyperdrive(Short multiplicateurHyperdrive);

}