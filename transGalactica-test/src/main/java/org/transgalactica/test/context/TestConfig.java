package org.transgalactica.test.context;

import java.io.IOException;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class TestConfig {

	protected TestConfig() {
	}

	@Bean
	public static PropertyPlaceholderConfigurer testPropertyPlaceholderConfigurer() throws IOException {
		// @PropertySource non possible, car utilisation de @Value
		PropertyPlaceholderConfigurer props = new PropertyPlaceholderConfigurer();
		props.setLocations(new Resource[] { new ClassPathResource("transGalacticaTest.properties") });
		return props;
	}
}
