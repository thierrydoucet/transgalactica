package org.transgalactica.management.rest.logistics.data;

import java.io.Serializable;

public interface HangarDto extends Serializable {

	Long getNumero();

	String getLocalisation();

	int getNombreEmplacements();
}