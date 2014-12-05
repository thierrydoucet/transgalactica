package org.transgalactica.management.data.people.bo;

import java.io.Serializable;
import java.util.Date;

public interface EmployeSearchCriteria extends Serializable {

	String getNomEmploye();

	void setNomEmploye(String nomEmploye);

	Date getDateEmbaucheEmployeDebut();

	void setDateEmbaucheEmployeDebut(Date dateEmbaucheEmployeDebut);

	Date getDateEmbaucheEmployeFin();

	void setDateEmbaucheEmployeFin(Date dateEmbaucheEmployeFin);

	void setImmatriculationVaisseau(String immatriculationVaisseau);

	String getImmatriculationVaisseau();
}
