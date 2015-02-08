package org.transgalactica.management.ws.context;

import java.io.IOException;

import org.dozer.BeanFactory;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.transgalactica.fwk.domain.bean.factory.SpringContextDozerFactory;

@Configuration
public class MapperConfig {

	@Bean
	public BeanFactory springContextDozerFactory() {
		return new SpringContextDozerFactory();
	}

	@Bean
	public DozerBeanMapperFactoryBean mapper() throws IOException {
		return new DozerBeanMapperFactoryBean();
	}
}