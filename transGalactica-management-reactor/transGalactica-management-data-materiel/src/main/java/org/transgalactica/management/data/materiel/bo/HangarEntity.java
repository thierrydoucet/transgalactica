package org.transgalactica.management.data.materiel.bo;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public interface HangarEntity extends Serializable {

	Long getNumero();

	@NotBlank
	@Size(max = 50)
	String getLocalisation();

	void setLocalisation(String localisation);

	@NotNull
	@Min(1)
	int getNombreEmplacements();

	void setNombreEmplacements(int nombreEmplacements);

	Set<VaisseauEntity> getVaisseaux();

	boolean add(VaisseauEntity vaisseau);

	boolean remove(VaisseauEntity vaisseau);
}
