package org.transgalactica.management.data.materiel.bo;

import java.io.Serializable;

public interface HangarSummary extends Serializable {

	Long getNumeroHangar();

	String getLocalisationHangar();

	int getNombreEmplacementsHangar();
}
