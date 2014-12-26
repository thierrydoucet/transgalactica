package org.transgalactica.management.data.rest.bo;

public interface VaisseauTo extends VaisseauSummaryTo {

	short getNombreDePassagers();

	void setNombreDePassagers(short nombreDePassagers);

	long getCapaciteDeFret();

	void setCapaciteDeFret(long capaciteDeFret);

	int getVitesse();

	void setVitesse(int vitesse);

	int getAutonomie();

	void setAutonomie(int autonomie);

	Long getNumeroHangar();

	Short getMultiplicateurHyperdrive();

	void setMultiplicateurHyperdrive(Short multiplicateurHyperdrive);

	void setImmatriculation(String immatriculation);

	void setModele(String modele);

}