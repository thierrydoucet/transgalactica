package org.transgalactica.management.rest.hr.restservice;

import java.util.Date;

import org.transgalactica.management.rest.hr.data.EmployeCommand;
import org.transgalactica.management.rest.hr.data.EmployeDetailDto;
import org.transgalactica.management.rest.hr.data.EmployeDtos;

public interface EmployeRestService {

	EmployeDetailDto getByMatricule(long matricule);

	void create(EmployeCommand command);

	void save(long matricule, EmployeCommand command);

	void delete(long matricule);

	EmployeDtos search(String nomEmploye, Date dateEmbaucheEmployeDebut, Date dateEmbaucheEmployeFin,
			String immatriculationVaisseau);

	void addVaisseau(long matricule, String immatriculation);

	void removeVaisseau(long matricule, String immatriculation);

	void addMecanicienSpecialite(long matricule, String specialite);

	void removeMecanicienSpecialite(long matricule, String specialite);
}
