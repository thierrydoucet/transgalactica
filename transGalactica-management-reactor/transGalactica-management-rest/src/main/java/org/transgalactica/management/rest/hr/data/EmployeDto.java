package org.transgalactica.management.rest.hr.data;

import java.io.Serializable;
import java.time.LocalDate;

import org.transgalactica.management.data.referentiel.bo.EmployeType;

public interface EmployeDto extends Serializable {

	Long getMatricule();

	String getNom();

	LocalDate getDateEmbauche();

	EmployeType getTypeEmploye();
}