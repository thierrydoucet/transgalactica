package org.transgalactica.management.data.materiel.bo;

import java.io.Serializable;

public interface VaisseauSummary extends Serializable {

	String getModeleVaisseau();

	String getImmatriculationVaisseau();

	String getLocalisationHangar();
}
