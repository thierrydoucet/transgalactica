package org.transgalactica.management.data.people.bo;

import java.io.Serializable;
import java.time.LocalDate;

import org.transgalactica.management.data.referentiel.bo.EmployeType;

public interface EmployeSummary extends Serializable {

	Long getMatriculeEmploye();

	String getNomEmploye();

	LocalDate getDateEmbaucheEmploye();

	EmployeType getTypeEmploye();
}
