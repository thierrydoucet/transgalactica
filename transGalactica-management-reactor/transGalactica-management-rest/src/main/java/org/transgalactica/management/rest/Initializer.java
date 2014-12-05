package org.transgalactica.management.rest;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.transgalactica.management.rest.context.AppConfig;
import org.transgalactica.management.rest.context.mvc.WebMvcConfig;

public class Initializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {
		// Create filters
		FilterRegistration.Dynamic charEncodingfilter = container.addFilter("characterEncodingFilter",
				CharacterEncodingFilter.class);
		charEncodingfilter.setInitParameter("encoding", "UTF-8");
		charEncodingfilter.setInitParameter("forceEncoding", "true");
		charEncodingfilter.addMappingForUrlPatterns(null, false, "/*");
		registerFilter(container, "simpleCORSFilter", SimpleCORSFilter.class);
		registerFilter(container, "localizationFilter", RequestContextFilter.class);
		registerFilter(container, "hiddenHttpMethodFilter", HiddenHttpMethodFilter.class);
		registerFilter(container, "springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"));
		registerFilter(container, "openEntityManagerInViewFilter", OpenEntityManagerInViewFilter.class);

		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.setId("root");
		rootContext.getEnvironment().setActiveProfiles("embedded");
		rootContext.register(AppConfig.class);
		container.addListener(new ContextLoaderListener(rootContext));

		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
		mvcContext.setId("mvc");
		mvcContext.register(WebMvcConfig.class);
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(mvcContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

	private void registerFilter(ServletContext container, String name, Filter filter) {
		container.addFilter(name, filter).addMappingForUrlPatterns(null, false, "/*");
	}

	private void registerFilter(ServletContext container, String name, Class<? extends Filter> filterClazz) {
		container.addFilter(name, filterClazz).addMappingForUrlPatterns(null, false, "/*");
	}
}
