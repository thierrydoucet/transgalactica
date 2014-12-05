package org.transgalactica.management.rest.context.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.transgalactica.management.rest.converter.HeaderTypeHttpMessageConverter;
import org.transgalactica.management.rest.converter.Jaxb2AbstractTypeHttpMessageConverter;
import org.transgalactica.management.rest.hr.data.EmployeCommand;
import org.transgalactica.management.rest.hr.data.MecanicienDetailDto;
import org.transgalactica.management.rest.hr.data.PiloteCommand;
import org.transgalactica.management.rest.hr.data.PiloteDetailDto;
import org.transgalactica.management.rest.hr.data.impl.JaxbEmployeCommand;
import org.transgalactica.management.rest.hr.data.impl.JaxbPiloteCommand;
import org.transgalactica.management.rest.logistics.data.HangarCommand;
import org.transgalactica.management.rest.logistics.data.VaisseauCommand;
import org.transgalactica.management.rest.logistics.data.impl.JaxbHangarCommand;
import org.transgalactica.management.rest.logistics.data.impl.JaxbVaisseauCommand;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
@EnableWebMvc
@Import(DozerConfig.class)
@ComponentScan({ "org.transgalactica.management.rest.hr", "org.transgalactica.management.rest.logistics" })
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private ApplicationContext context;

	protected WebMvcConfig() {
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable("default");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		jsonConverter.setObjectMapper(jacksonObjectMapper());

		HeaderTypeHttpMessageConverter<Object> headerJsonConverter = new HeaderTypeHttpMessageConverter<>(jsonConverter);
		headerJsonConverter.addMapping("PiloteCommand", PiloteCommand.class) //
				.addMapping("EmployeCommand", EmployeCommand.class) //
				.addMapping("MecanicienDetail", MecanicienDetailDto.class) //
				.addMapping("PiloteDetail", PiloteDetailDto.class);

		converters.add(headerJsonConverter);

		Jaxb2AbstractTypeHttpMessageConverter jaxbConverter = new Jaxb2AbstractTypeHttpMessageConverter();
		jaxbConverter.addTypeMapping(HangarCommand.class, JaxbHangarCommand.class)
				.addTypeMapping(VaisseauCommand.class, JaxbVaisseauCommand.class)
				.addTypeMapping(EmployeCommand.class, JaxbEmployeCommand.class)
				.addTypeMapping(PiloteCommand.class, JaxbPiloteCommand.class);

		HeaderTypeHttpMessageConverter<Object> headerJaxbConverter = new HeaderTypeHttpMessageConverter<>(jaxbConverter);
		headerJaxbConverter.addMapping("PiloteCommand", PiloteCommand.class) //
				.addMapping("EmployeCommand", EmployeCommand.class) //
				.addMapping("MecanicienDetail", MecanicienDetailDto.class) //
				.addMapping("PiloteDetail", PiloteDetailDto.class);

		converters.add(headerJaxbConverter);
	}

	@Override
	public Validator getValidator() {
		// TODO: voir si on peut utiliser la notion d ioc pour injecter a la
		// couche business les dependance tel que la validation / les
		// transactions / la datasource ?
		return context.getBean("javax.validation.ValidatorFactory", Validator.class);
	}

	@Bean
	public ObjectMapper jacksonObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addAbstractTypeMapping(HangarCommand.class, JaxbHangarCommand.class);
		module.addAbstractTypeMapping(VaisseauCommand.class, JaxbVaisseauCommand.class);
		module.addAbstractTypeMapping(EmployeCommand.class, JaxbEmployeCommand.class);
		module.addAbstractTypeMapping(PiloteCommand.class, JaxbPiloteCommand.class);
		mapper.registerModule(module);
		return mapper;
	}
}