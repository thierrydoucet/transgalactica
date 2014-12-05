package org.transgalactica.management.rest.context.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.transgalactica.fwk.domain.bean.factory.SpringContextDozerFactory;

@Configuration
public class DozerConfig {

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private ResourcePatternResolver resourceLoader;

	protected DozerConfig() {
	}

	// Surcharge du mapper de data-core, afin de voir les databean du context
	// MVC
	@Bean(name = "org.dozer.Mapper")
	public Mapper mapper() throws IOException {
		DozerBeanMapper mapper = new DozerBeanMapper();

		mapper.setFactories(Collections.singletonMap("contextFactory",
				(org.dozer.BeanFactory) new SpringContextDozerFactory(beanFactory)));
		final List<String> mappings = new ArrayList<>();
		for (Resource mappingFile : resourceLoader.getResources("classpath*:org/transgalactica/**/mapper/*.dozer.xml")) {
			mappings.add(mappingFile.getURL().toString());
		}
		mapper.setMappingFiles(mappings);

		return mapper;
	}
}
