package org.transgalactica.management.rest.logistics.mapper.impl;

import static org.dozer.loader.api.FieldsMappingOptions.hintB;
import static org.dozer.loader.api.TypeMappingOptions.oneWay;

import java.util.List;

import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldDefinition;
import org.springframework.stereotype.Component;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauIntergalactiqueEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;
import org.transgalactica.management.rest.logistics.data.VaisseauCommand;
import org.transgalactica.management.rest.logistics.data.VaisseauDetailDto;
import org.transgalactica.management.rest.logistics.data.VaisseauDto;
import org.transgalactica.management.rest.logistics.data.VaisseauDtos;

@Component
public class DozerVaisseauMappingBuilder extends BeanMappingBuilder {

	protected DozerVaisseauMappingBuilder() {
	}

	@Override
	protected void configure() {
		mapping(VaisseauCommand.class, type(VaisseauEntity.class).beanFactory("springContextDozerFactory"), oneWay());
		mapping(VaisseauCommand.class,
				type(VaisseauIntergalactiqueEntity.class).beanFactory("springContextDozerFactory"), oneWay());

		mapping(List.class, type(VaisseauDtos.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("this", new FieldDefinition("vaisseaux") //
						.iterate() //
						.setMethod("add(org.transgalactica.management.rest.logistics.data.VaisseauDto)") //
						, hintB(VaisseauDto.class));

		// Déclaration des champs de meme nom obligatoire, car le setter n'est
		// pas dans l'interface

		mapping(VaisseauSummary.class, type(VaisseauDto.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("immatriculationVaisseau", "immatriculation")//
				.fields("modeleVaisseau", "modele").fields("localisationHangar", "localisationHangar");

		mapping(VaisseauEntity.class, type(VaisseauDetailDto.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("immatriculation", "immatriculation") //
				.fields("modele", "modele") //
				.fields("nombreDePassagers", "nombreDePassagers") //
				.fields("capaciteDeFret", "capaciteDeFret") //
				.fields("vitesse", "vitesse") //
				.fields("autonomie", "autonomie") //
				.fields("hangar.numero", "numeroHangar") //
				.fields("hangar.localisation", "localisationHangar");

		mapping(VaisseauIntergalactiqueEntity.class,
				type(VaisseauDetailDto.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("multiplicateurHyperdrive", "multiplicateurHyperdrive");
	}
}