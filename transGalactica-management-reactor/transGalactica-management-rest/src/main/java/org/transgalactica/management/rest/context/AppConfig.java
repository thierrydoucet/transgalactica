package org.transgalactica.management.rest.context;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
// TODO : component scan & beanRefFactory
@ComponentScan(basePackages = { "org.transgalactica" })
@ImportResource({ "classpath*:META-INF/beanRefFactory.xml",
		"classpath:org/transgalactica/management/rest/config/context/security.spring.xml" })
// TODO quand dispo : @Import(SecurityConfig.class)
public class AppConfig {

	// private ResourceLoader loader = new DefaultResourceLoader();

	protected AppConfig() {
	}

	// @Bean
	// public PropertyPlaceholderConfigurer restPropertyConfigurer() throws
	// IOException {
	// // TODO voir pour utiliser @Environement & @PropertySource /
	// PropertyPlaceholderConfigurer props = new
	// org.springframework.beans.factory.config.PropertyPlaceholderConfigurer();
	// props.setIgnoreResourceNotFound(false);
	// props.setFileEncoding("UTF-8");
	// String path = System.getProperty("transGalactica.configuration",
	// "classpath:transGalactica.properties");
	// props.setLocation(loader.getResource(path));
	// return props;
	// }

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("org/springframework/security/messages");
		return messageSource;
	}
}
