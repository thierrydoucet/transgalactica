package org.transgalactica.management.rest.logistics.data;

import java.io.Serializable;
import java.util.List;


public interface VaisseauDtos extends Serializable {

	List<VaisseauDto> getVaisseaux();
}