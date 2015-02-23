package org.transgalactica.management.rest.logistics.mapper.impl;

import static org.dozer.loader.api.FieldsMappingOptions.hintB;
import static org.dozer.loader.api.TypeMappingOptions.oneWay;

import java.util.List;

import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldDefinition;
import org.springframework.stereotype.Component;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.HangarSearchCriteria;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.rest.logistics.data.HangarCommand;
import org.transgalactica.management.rest.logistics.data.HangarDetailDto;
import org.transgalactica.management.rest.logistics.data.HangarDto;
import org.transgalactica.management.rest.logistics.data.HangarDtos;

@Component
public class DozerHangarMappingBuilder extends BeanMappingBuilder {

	protected DozerHangarMappingBuilder() {
	}

	@Override
	protected void configure() {
		mapping(HangarCommand.class, type(HangarEntity.class).beanFactory("springContextDozerFactory"), oneWay());

		mapping(List.class, type(HangarDtos.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("this", new FieldDefinition("hangars") //
						.iterate() //
						.setMethod("add(org.transgalactica.management.rest.logistics.data.HangarDto)") //
						, hintB(HangarDto.class));

		mapping(HangarSummary.class, type(HangarDto.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("numeroHangar", "numero") //
				.fields("localisationHangar", "localisation") //
				.fields("nombreEmplacementsHangar", "nombreEmplacements");

		// Déclaration des champs de meme nom obligatoire, car le setter n'est
		// pas dans l'interface

		mapping(HangarEntity.class, type(HangarDetailDto.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("numero", "numero") //
				.fields("localisation", "localisation") //
				.fields("nombreEmplacements", "nombreEmplacements") //
				.fields("vaisseaux", "vaisseaux");

		mapping(VaisseauEntity.class, type(HangarDetailDto.VaisseauDto.class).beanFactory("springContextDozerFactory"),
				oneWay()) //
				.fields("immatriculation", "immatriculation") //
				.fields("modele", "modele") //
				.fields("nombreDePassagers", "nombreDePassagers") //
				.fields("capaciteDeFret", "capaciteDeFret") //
				.fields("vitesse", "vitesse") //
				.fields("autonomie", "autonomie");

		mapping(String.class, type(HangarSearchCriteria.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("this", "localisationHangar");
	}
}