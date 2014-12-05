package org.transgalactica.data.rest.bo;

import java.io.Serializable;

public interface HangarSummaryTo extends Serializable {

	Long getNumero();

	String getLocalisation();

	int getNombreEmplacements();

}