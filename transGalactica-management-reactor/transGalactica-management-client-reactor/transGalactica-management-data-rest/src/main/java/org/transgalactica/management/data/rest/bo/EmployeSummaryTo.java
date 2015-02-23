package org.transgalactica.management.data.rest.bo;

import java.io.Serializable;
import java.time.LocalDate;

public interface EmployeSummaryTo extends Serializable {

	Long getMatricule();

	String getNom();

	String getTypeEmploye();

	LocalDate getDateEmbauche();
}