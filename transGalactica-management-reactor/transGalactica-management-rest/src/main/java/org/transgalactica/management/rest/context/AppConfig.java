package org.transgalactica.management.rest.context;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@Import({ SecurityConfig.class, ValidationConfig.class, MapperConfig.class, PersistenceConfig.class })
@ComponentScan({ "org.transgalactica.management.business.hr", //
		"org.transgalactica.management.business.logistics", //
		"org.transgalactica.management.data.materiel", //
		"org.transgalactica.management.data.referentiel", //
		"org.transgalactica.management.data.people" })
public class AppConfig {

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("org/springframework/security/messages");
		return messageSource;
	}
}
