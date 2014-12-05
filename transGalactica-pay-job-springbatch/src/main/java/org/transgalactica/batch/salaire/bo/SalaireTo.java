package org.transgalactica.batch.salaire.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.transgalactica.management.data.referentiel.bo.EmployeType;

public interface SalaireTo extends Serializable {

	void setSalaire(BigDecimal salaire);

	BigDecimal getSalaire();

	void setPrimeExperience(BigDecimal primeExperience);

	BigDecimal getPrimeExperience();

	void setPrimeAnciennete(BigDecimal primeAnciennete);

	BigDecimal getPrimeAnciennete();

	void setSalaireBase(BigDecimal salaireBase);

	BigDecimal getSalaireBase();

	void setTypeEmploye(EmployeType typeEmploye);

	EmployeType getTypeEmploye();

	void setDateEmbaucheEmploye(Date dateEmbaucheEmploye);

	Date getDateEmbaucheEmploye();

	void setNomEmploye(String nomEmploye);

	String getNomEmploye();

}