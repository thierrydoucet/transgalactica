package org.transgalactica.management.data.people.bo;

import java.io.Serializable;
import java.util.Date;

import org.transgalactica.management.data.referentiel.bo.EmployeType;

public interface EmployeSummary extends Serializable {

	Long getMatriculeEmploye();

	String getNomEmploye();

	Date getDateEmbaucheEmploye();

	EmployeType getTypeEmploye();
}
