package org.transgalactica.management.rest.logistics.data;

import java.io.Serializable;

public interface VaisseauDto extends Serializable {

	String getImmatriculation();

	String getModele();

	String getLocalisationHangar();
}