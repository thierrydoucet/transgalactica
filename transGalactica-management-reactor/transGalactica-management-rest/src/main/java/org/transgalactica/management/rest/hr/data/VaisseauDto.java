package org.transgalactica.management.rest.hr.data;

import java.io.Serializable;

public interface VaisseauDto extends Serializable {

	String getImmatriculation();

	String getModele();

	short getNombreDePassagers();

	long getCapaciteDeFret();

	int getVitesse();

	int getAutonomie();
}