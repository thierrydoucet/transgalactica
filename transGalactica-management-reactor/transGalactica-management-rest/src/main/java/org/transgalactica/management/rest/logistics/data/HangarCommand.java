package org.transgalactica.management.rest.logistics.data;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public interface HangarCommand extends Serializable {

	@NotBlank
	@Size(max = 30)
	String getLocalisation();

	void setLocalisation(String localisation);

	@NotNull
	@Min(1)
	Integer getNombreEmplacements();

	void setNombreEmplacements(Integer nombreEmplacement);
}