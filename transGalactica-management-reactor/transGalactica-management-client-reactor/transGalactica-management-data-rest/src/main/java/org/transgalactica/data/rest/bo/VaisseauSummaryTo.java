package org.transgalactica.data.rest.bo;

import java.io.Serializable;

public interface VaisseauSummaryTo extends Serializable {

	String getImmatriculation();

	String getModele();

	String getLocalisationHangar();

}