package org.transgalactica.management.data.rest;

import java.io.IOException;

import org.dozer.BeanFactory;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.transgalactica.fwk.domain.bean.factory.SpringContextDozerFactory;

@Configuration
@ComponentScan
@PropertySource("classpath:transGalactica.properties")
@ImportResource("classpath:org/transgalactica/management/data/rest/dao/mock.spring.xml")
public class TestConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholder() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public BeanFactory springContextDozerFactory() {
		return new SpringContextDozerFactory();
	}

	@Bean
	public DozerBeanMapperFactoryBean mapper() throws IOException {
		return new DozerBeanMapperFactoryBean();
	}
}
