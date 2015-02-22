package org.transgalactica.info.data.motd;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.dozer.BeanFactory;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.transgalactica.fwk.domain.bean.factory.SpringContextDozerFactory;

@Configuration
@ComponentScan("org.transgalactica.info")
public class TestContext {

	@Autowired
	private List<BeanMappingBuilder> mappingBuilders;

	@Bean
	public static PropertyPlaceholderConfigurer testPropertyPlaceholderConfigurer() throws IOException {
		Properties properties = new Properties();
		properties.put("motd.url", "classpath:org/transgalactica/info/data/motd/mock/motd.xml");
		PropertyPlaceholderConfigurer props = new PropertyPlaceholderConfigurer();
		props.setProperties(properties);
		return props;
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
