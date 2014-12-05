package org.transgalactica.management.rest.hr.data;

import java.io.Serializable;
import java.util.Date;

import org.transgalactica.management.data.referentiel.bo.EmployeType;

public interface EmployeDto extends Serializable {

	Long getMatricule();

	String getNom();

	Date getDateEmbauche();

	EmployeType getTypeEmploye();
}