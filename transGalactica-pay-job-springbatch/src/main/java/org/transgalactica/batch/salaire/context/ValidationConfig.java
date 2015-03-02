package org.transgalactica.batch.salaire.context;

import javax.validation.ValidatorFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
public class ValidationConfig {

	@Bean
	public MethodValidationPostProcessor registerMethodValidation() {
		return new MethodValidationPostProcessor();
	}

	@Bean
	public ValidatorFactory validator() {
		return new LocalValidatorFactoryBean();
	}
}
