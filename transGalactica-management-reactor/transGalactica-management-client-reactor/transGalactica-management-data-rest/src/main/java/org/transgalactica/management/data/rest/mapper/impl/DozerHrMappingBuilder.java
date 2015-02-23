package org.transgalactica.management.data.rest.mapper.impl;

import static org.dozer.loader.api.FieldsMappingOptions.customConverter;
import static org.dozer.loader.api.TypeMappingOptions.oneWay;

import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;
import org.transgalactica.management.data.rest.bo.EmployeSummaryTo;
import org.transgalactica.management.data.rest.bo.EmployeTo;
import org.transgalactica.management.data.rest.bo.MecanicienTo;
import org.transgalactica.management.data.rest.bo.PiloteTo;
import org.transgalactica.management.flux.rest.EmployeCommand;
import org.transgalactica.management.flux.rest.EmployeDto;
import org.transgalactica.management.flux.rest.EmployeVaisseau;
import org.transgalactica.management.flux.rest.MecanicienDetailDto;
import org.transgalactica.management.flux.rest.PiloteCommand;
import org.transgalactica.management.flux.rest.PiloteDetailDto;

@Component
public class DozerHrMappingBuilder extends BeanMappingBuilder {

	protected DozerHrMappingBuilder() {
	}

	@Override
	protected void configure() {
		mapping(PiloteTo.class, type(PiloteCommand.class).beanFactory("org.dozer.factory.JAXBBeanFactory"), oneWay())
				.fields("dateEmbauche", "dateEmbauche", customConverter(XMLGregorianCalendarToLocalDateConverter.class));

		mapping(MecanicienTo.class, type(EmployeCommand.class).beanFactory("org.dozer.factory.JAXBBeanFactory"),
				oneWay()) //
				.fields("dateEmbauche", "dateEmbauche", customConverter(XMLGregorianCalendarToLocalDateConverter.class));

		mapping(EmployeDto.class, type(EmployeSummaryTo.class).beanFactory("springContextDozerFactory"), oneWay())
				.fields("matricule", "matricule")
				.fields("nom", "nom")
				.fields("dateEmbauche", "dateEmbauche", customConverter(XMLGregorianCalendarToLocalDateConverter.class)) //
				.fields("typeEmploye", "typeEmploye");

		mapping(PiloteDetailDto.class, type(PiloteTo.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("vaisseaux.vaisseau", "vaisseaux") //
				.fields("nombreHeuresVol", "nombreHeuresVol");

		mapping(MecanicienDetailDto.class, type(MecanicienTo.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("vaisseaux.vaisseau", "vaisseaux") //
				.fields("specialites.specialite", "specialites");

		mapping(EmployeVaisseau.class, type(EmployeTo.VaisseauTo.class).beanFactory("springContextDozerFactory"),
				oneWay()) //
				.fields("immatriculation", "immatriculation") //
				.fields("modele", "modele") //
				.fields("nombreDePassagers", "nombreDePassagers") //
				.fields("capaciteDeFret", "capaciteDeFret") //
				.fields("vitesse", "vitesse") //
				.fields("autonomie", "autonomie");
	}
}
