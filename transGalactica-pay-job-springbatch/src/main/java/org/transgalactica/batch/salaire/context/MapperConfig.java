package org.transgalactica.batch.salaire.context;

import java.io.IOException;

import org.dozer.BeanFactory;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.transgalactica.fwk.domain.bean.factory.SpringContextDozerFactory;

@Configuration
public class MapperConfig {

	@Bean
	public BeanFactory springContextDozerFactory() {
		return new SpringContextDozerFactory();
	}

	@Bean
	public DozerBeanMapperFactoryBean mapper() throws IOException {
		DozerBeanMapperFactoryBean mapper = new DozerBeanMapperFactoryBean();
		mapper.setMappingFiles(new Resource[] { new ClassPathResource("global-configuration.dozer.xml") });
		return mapper;
	}
}