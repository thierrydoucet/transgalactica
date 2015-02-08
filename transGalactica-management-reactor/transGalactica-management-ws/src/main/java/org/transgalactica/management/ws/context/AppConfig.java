package org.transgalactica.management.ws.context;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
// @Import({ SecurityConfig.class, ValidationConfig.class, MapperConfig.class,
// PersistenceConfig.class })
@ComponentScan({ "org.transgalactica.management.business.logistics", //
		"org.transgalactica.management.data.materiel", })
public class AppConfig {

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("org/springframework/security/messages", "i18n/exceptions");
		return messageSource;
	}
}