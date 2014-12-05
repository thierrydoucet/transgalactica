package org.transgalactica.management.rest.hr.data;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public interface EmployeCommand extends Serializable {

	@NotNull
	@NotBlank
	String getNom();

	void setNom(String nom);

	Date getDateEmbauche();

	void setDateEmbauche(Date dateEmbauche);
}