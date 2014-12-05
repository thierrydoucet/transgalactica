package org.transgalactica.management.rest.logistics.data;

import java.io.Serializable;

public interface VaisseauDetailDto extends VaisseauDto, Serializable {

	short getNombreDePassagers();

	long getCapaciteDeFret();

	int getVitesse();

	int getAutonomie();

	Short getMultiplicateurHyperdrive();

	Long getNumeroHangar();
}