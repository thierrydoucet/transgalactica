package org.transgalactica.management.data.rest.bo;

import java.io.Serializable;

public interface VaisseauSearchCriteria extends Serializable {

	String getImmatriculation();

	void setImmatriculation(String immatriculation);

	String getModele();

	void setModele(String modele);

	boolean isIntergalactique();

	void setIntergalactique(boolean intergalactique);

}