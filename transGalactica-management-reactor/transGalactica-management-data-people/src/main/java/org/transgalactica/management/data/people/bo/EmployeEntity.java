package org.transgalactica.management.data.people.bo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.referentiel.bo.EmployeType;

public interface EmployeEntity extends Serializable {

	Long getMatricule();

	@NotBlank
	@Size(max = 50)
	String getNom();

	void setNom(String nom);

	LocalDate getDateEmbauche();

	void setDateEmbauche(LocalDate dateEmbauche);

	EmployeType getType();

	Set<VaisseauEntity> getVaisseaux();

	boolean addVaisseau(VaisseauEntity vaisseau);

	boolean removeVaisseau(VaisseauEntity vaisseau);
}
