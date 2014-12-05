package org.transgalactica.management.business.hr.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.people.bo.EmployeSearchCriteria;
import org.transgalactica.management.data.people.bo.EmployeSummary;
import org.transgalactica.management.data.referentiel.bo.MecanicienSpecialiteEntity;

@Validated
public interface EmployeService {

	List<EmployeSummary> rechercherEmployes(@NotNull @Valid EmployeSearchCriteria criteresRechercheEmploye);

	EmployeEntity chargerEmploye(Long matricule);

	void enregistrerEmploye(@NotNull @Valid EmployeEntity employe);

	void supprimerEmploye(@NotNull EmployeEntity employe);

	void affecterVaisseauAEmploye(@NotNull VaisseauEntity vaisseau, @NotNull EmployeEntity employe);

	MecanicienSpecialiteEntity chargerMecanicienSpecialite(@NotNull String nomSpecialite);

	List<MecanicienSpecialiteEntity> chargerMecanicienSpecialites();
}
