package org.transgalactica.management.data.rest.bo;

import java.io.Serializable;
import java.time.LocalDate;

public interface EmployeSearchCriteria extends Serializable {

	String getNomEmploye();

	void setNomEmploye(String nomEmploye);

	LocalDate getDateEmbaucheEmployeDebut();

	void setDateEmbaucheEmployeDebut(LocalDate dateEmbaucheEmployeDebut);

	LocalDate getDateEmbaucheEmployeFin();

	void setDateEmbaucheEmployeFin(LocalDate dateEmbaucheEmployeFin);
}