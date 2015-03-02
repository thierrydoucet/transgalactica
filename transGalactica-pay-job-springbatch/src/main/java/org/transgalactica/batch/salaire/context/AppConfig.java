package org.transgalactica.batch.salaire.context;

import java.time.LocalDate;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@Import({ JobConfig.class, ValidationConfig.class, MapperConfig.class, PersistenceConfig.class })
@ComponentScan(basePackages = { "org.transgalactica.batch.salaire", //
		"org.transgalactica.management.data.materiel", //
		"org.transgalactica.management.data.referentiel", //
		"org.transgalactica.management.data.people" }, excludeFilters = @Filter(type = FilterType.ANNOTATION, value = Configuration.class))
public class AppConfig {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setBasenames("org/transgalactica/batch/salaire/i18n/messages", //
				"org/transgalactica/batch/salaire/i18n/images", //
				"org/transgalactica/batch/salaire/i18n/fonts");
		return messageSource;
	}

	@Bean
	public static CustomEditorConfigurer localDateEditorConfigurer() {
		CustomEditorConfigurer configurer = new CustomEditorConfigurer();
		configurer.setPropertyEditorRegistrars(new PropertyEditorRegistrar[] { //
				(registry) -> registry.registerCustomEditor(LocalDate.class, new LocalDatePropertyEditor()) });
		return configurer;
	}
}
