package org.transgalactica.management.rest.context;

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
public class MapperConfig {

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private ResourcePatternResolver resourceLoader;

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

// <bean
// id="org.dozer.Mapper"
// class="org.dozer.spring.DozerBeanMapperFactoryBean"
// p:mappingFiles="classpath*:org/transgalactica/**/mapper/*.dozer.xml">
// <property
// name="factories">
// <map>
// <entry
// key="contextFactory">
// <bean
// class="org.transgalactica.fwk.domain.bean.factory.SpringContextDozerFactory"
// />
// </entry>
// </map>
// </property>
// </bean>

// @Bean
// public BeanFactory springContextDozerFactory() {
// return new SpringContextDozerFactory();
// }
//
// @Bean
// public DozerBeanMapperFactoryBean mapper() throws IOException {
// return new DozerBeanMapperFactoryBean();
// }