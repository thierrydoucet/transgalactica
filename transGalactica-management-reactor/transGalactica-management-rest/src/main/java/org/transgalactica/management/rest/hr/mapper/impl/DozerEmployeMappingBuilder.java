package org.transgalactica.management.rest.hr.mapper.impl;

import static org.dozer.loader.api.FieldsMappingOptions.copyByReference;
import static org.dozer.loader.api.FieldsMappingOptions.hintB;
import static org.dozer.loader.api.TypeMappingOptions.oneWay;

import java.util.List;

import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldDefinition;
import org.springframework.stereotype.Component;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.people.bo.EmployeSummary;
import org.transgalactica.management.data.people.bo.MecanicienEntity;
import org.transgalactica.management.data.people.bo.PiloteEntity;
import org.transgalactica.management.rest.hr.data.EmployeCommand;
import org.transgalactica.management.rest.hr.data.EmployeDto;
import org.transgalactica.management.rest.hr.data.EmployeDtos;
import org.transgalactica.management.rest.hr.data.MecanicienDetailDto;
import org.transgalactica.management.rest.hr.data.PiloteCommand;
import org.transgalactica.management.rest.hr.data.PiloteDetailDto;
import org.transgalactica.management.rest.hr.data.VaisseauDto;

@Component
public class DozerEmployeMappingBuilder extends BeanMappingBuilder {

	protected DozerEmployeMappingBuilder() {
	}

	@Override
	protected void configure() {
		mapping(PiloteCommand.class, type(PiloteEntity.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("dateEmbauche", "dateEmbauche", copyByReference());
		mapping(EmployeCommand.class, type(MecanicienEntity.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("dateEmbauche", "dateEmbauche", copyByReference());

		mapping(List.class, type(EmployeDtos.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("this", new FieldDefinition("employes") //
						.iterate() //
						.setMethod("add(org.transgalactica.management.rest.hr.data.EmployeDto)") //
						, hintB(EmployeDto.class));

		mapping(EmployeSummary.class, type(EmployeDto.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("matriculeEmploye", "matricule") //
				.fields("nomEmploye", "nom") //
				.fields("dateEmbaucheEmploye", "dateEmbauche", copyByReference()) //
				.fields("typeEmploye", "typeEmploye");

		// Déclaration des champs de meme nom obligatoire, car le setter n'est
		// pas dans l'interface

		mapping(PiloteEntity.class, type(PiloteDetailDto.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("matricule", "matricule") //
				.fields("nom", "nom") //
				.fields("dateEmbauche", "dateEmbauche", copyByReference()) //
				.fields("type", "typeEmploye") //
				.fields("nombreHeuresVol", "nombreHeuresVol") //
				.fields("vaisseaux", "vaisseaux");

		mapping(MecanicienEntity.class, type(MecanicienDetailDto.class).beanFactory("springContextDozerFactory"),
				oneWay()) //
				.fields("matricule", "matricule") //
				.fields("nom", "nom") //
				.fields("dateEmbauche", "dateEmbauche", copyByReference()) //
				.fields("type", "typeEmploye") //
				.fields("vaisseaux", "vaisseaux") //
				.fields("specialites", "specialites");

		mapping(VaisseauEntity.class, type(VaisseauDto.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("immatriculation", "immatriculation") //
				.fields("modele", "modele") //
				.fields("nombreDePassagers", "nombreDePassagers") //
				.fields("capaciteDeFret", "capaciteDeFret") //
				.fields("vitesse", "vitesse") //
				.fields("autonomie", "autonomie");
	}
}