package org.transgalactica.management.rest.context.mvc;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.transgalactica.management.rest.context.MapperConfig;
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
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

@Configuration
@EnableWebMvc
@Import(MapperConfig.class)
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

	@Bean
	public ObjectMapper jacksonObjectMapper() {
		SimpleModule typeModule = new SimpleModule() //
				.addAbstractTypeMapping(HangarCommand.class, JaxbHangarCommand.class) //
				.addAbstractTypeMapping(VaisseauCommand.class, JaxbVaisseauCommand.class) //
				.addAbstractTypeMapping(EmployeCommand.class, JaxbEmployeCommand.class) //
				.addAbstractTypeMapping(PiloteCommand.class, JaxbPiloteCommand.class);
		return new ObjectMapper() //
				.disable(WRITE_DATES_AS_TIMESTAMPS) //
				.registerModules(typeModule, new JSR310Module());
	}
}