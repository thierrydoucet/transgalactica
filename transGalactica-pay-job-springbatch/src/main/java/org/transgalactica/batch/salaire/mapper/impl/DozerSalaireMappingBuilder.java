package org.transgalactica.batch.salaire.mapper.impl;

import static org.dozer.loader.api.FieldsMappingOptions.copyByReference;
import static org.dozer.loader.api.TypeMappingOptions.oneWay;

import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;
import org.transgalactica.batch.salaire.bo.SalaireTo;
import org.transgalactica.management.data.people.bo.EmployeEntity;

@Component
public class DozerSalaireMappingBuilder extends BeanMappingBuilder {

	protected DozerSalaireMappingBuilder() {
	}

	@Override
	protected void configure() {
		mapping(EmployeEntity.class, type(SalaireTo.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("nom", "nomEmploye") //
				.fields("dateEmbauche", "dateEmbaucheEmploye", copyByReference()) //
				.fields("type", "typeEmploye");
	}
}