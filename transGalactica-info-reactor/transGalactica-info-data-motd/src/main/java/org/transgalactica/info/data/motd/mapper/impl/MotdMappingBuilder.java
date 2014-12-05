package org.transgalactica.info.data.motd.mapper.impl;

import static org.dozer.loader.api.TypeMappingOptions.oneWay;

import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeDefinition;
import org.springframework.stereotype.Component;
import org.transgalactica.info.data.motd.bo.ImageTo;
import org.transgalactica.info.data.motd.bo.MessageTo;
import org.transgalactica.info.flux.motd.Image;
import org.transgalactica.info.flux.motd.Message;

@Component
public class MotdMappingBuilder extends BeanMappingBuilder {

	protected MotdMappingBuilder() {
	}

	@Override
	protected void configure() {
		mapping(Message.class, new TypeDefinition(MessageTo.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("id", "id") //
				.fields("titre", "titre") //
				.fields("contenu", "contenu") //
				.fields("datePublication", "datePublication") //
				.fields("image", "image");

		mapping(Image.class, new TypeDefinition(ImageTo.class).beanFactory("springContextDozerFactory"), oneWay()) //
				.fields("url", "url") //
				.fields("texteAlternatif", "texteAlternatif");
	}
}
