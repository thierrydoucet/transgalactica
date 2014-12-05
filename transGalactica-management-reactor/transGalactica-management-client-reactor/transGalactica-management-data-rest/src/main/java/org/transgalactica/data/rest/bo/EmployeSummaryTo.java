package org.transgalactica.data.rest.bo;

import java.io.Serializable;
import java.util.Date;

public interface EmployeSummaryTo extends Serializable {

	Long getMatricule();

	String getNom();

	String getTypeEmploye();

	Date getDateEmbauche();

}
