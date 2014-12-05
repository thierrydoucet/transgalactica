package org.transgalactica.management.data.materiel.bo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public interface HangarSearchCriteria extends Serializable {

	@NotBlank
	String getLocalisationHangar();

	void setLocalisationHangar(String localisationHangar);
}
