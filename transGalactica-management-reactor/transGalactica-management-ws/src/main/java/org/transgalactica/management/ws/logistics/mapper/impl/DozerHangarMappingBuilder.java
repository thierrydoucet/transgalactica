package org.transgalactica.management.ws.logistics.mapper.impl;

import static org.dozer.loader.api.TypeMappingOptions.oneWay;

import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.ws.logistics.data.BasicHangarDto;

@Component
public class DozerHangarMappingBuilder extends BeanMappingBuilder {

	protected DozerHangarMappingBuilder() {
	}

	@Override
	protected void configure() {
		mapping(HangarSummary.class, type(BasicHangarDto.class).beanFactory("springContextDozerFactory"), oneWay())
				.fields("numeroHangar", "numero") //
				.fields("localisationHangar", "localisation") //
				.fields("nombreEmplacementsHangar", "nombreEmplacements");
	}
}