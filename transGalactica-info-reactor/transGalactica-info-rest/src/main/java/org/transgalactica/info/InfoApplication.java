package org.transgalactica.info;

import java.io.IOException;

import org.dozer.BeanFactory;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.transgalactica.fwk.domain.bean.factory.SpringContextDozerFactory;

@Configuration
@ComponentScan
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class InfoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(InfoApplication.class, args);
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