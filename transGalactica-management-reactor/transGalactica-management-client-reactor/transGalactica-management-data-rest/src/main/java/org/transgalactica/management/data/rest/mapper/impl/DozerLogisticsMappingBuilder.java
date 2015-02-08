package org.transgalactica.management.data.rest.mapper.impl;

import static org.dozer.loader.api.TypeMappingOptions.oneWay;

import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeDefinition;
import org.springframework.stereotype.Component;
import org.transgalactica.management.data.rest.bo.HangarSummaryTo;
import org.transgalactica.management.data.rest.bo.HangarTo;
import org.transgalactica.management.data.rest.bo.VaisseauSummaryTo;
import org.transgalactica.management.data.rest.bo.VaisseauTo;
import org.transgalactica.management.flux.rest.HangarCommand;
import org.transgalactica.management.flux.rest.HangarDetailDto;
import org.transgalactica.management.flux.rest.HangarDto;
import org.transgalactica.management.flux.rest.HangarVaisseau;
import org.transgalactica.management.flux.rest.VaisseauCommand;
import org.transgalactica.management.flux.rest.VaisseauDetailDto;
import org.transgalactica.management.flux.rest.VaisseauDto;

@Component
public class DozerLogisticsMappingBuilder extends BeanMappingBuilder {

	protected DozerLogisticsMappingBuilder() {
	}

	@Override
	protected void configure() {
		// Hangars
		mapping(HangarTo.class, HangarCommand.class, oneWay());

		mapping(HangarDto.class, type(HangarSummaryTo.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("numero", "numero") //
				.fields("localisation", "localisation") //
				.fields("nombreEmplacements", "nombreEmplacements");

		mapping(HangarDetailDto.class, type(HangarTo.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("vaisseaux.vaisseau", "vaisseaux");

		mapping(HangarVaisseau.class, type(HangarTo.VaisseauTo.class).beanFactory("springContextDozerFactory"),
				oneWay()) //
				.fields("immatriculation", "immatriculation") //
				.fields("modele", "modele") //
				.fields("nombreDePassagers", "nombreDePassagers") //
				.fields("capaciteDeFret", "capaciteDeFret") //
				.fields("vitesse", "vitesse") //
				.fields("autonomie", "autonomie");

		// vaisseaux
		mapping(VaisseauTo.class, VaisseauCommand.class, oneWay());

		mapping(VaisseauDto.class, type(VaisseauSummaryTo.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("immatriculation", "immatriculation") //
				.fields("modele", "modele") //
				.fields("localisationHangar", "localisationHangar");

		mapping(VaisseauDetailDto.class, new TypeDefinition(VaisseauTo.class).beanFactory("springContextDozerFactory"),
				oneWay()) //
				.fields("vitesse", "vitesse") //
				.fields("autonomie", "autonomie") //
				.fields("nombreDePassagers", "nombreDePassagers") //
				.fields("capaciteDeFret", "capaciteDeFret") //
				.fields("multiplicateurHyperdrive", "multiplicateurHyperdrive") //
				.fields("numeroHangar", "numeroHangar");
	}
}