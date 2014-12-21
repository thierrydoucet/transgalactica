package org.transgalactica.management.rest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * Largement inspiré de http://spring.io/guides/gs/rest-service-cors/ et
 * https://gist.github.com/kdonald/2232095
 * 
 * TODO : surveiller https://jira.springsource.org/browse/SPR-9278
 * 
 * TODO : a remplacer par le CORS filter de Spring security
 * 
 * @author Thierry
 */
@Component
public class SimpleCORSFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
			// CORS "pre-flight" request
			response.addHeader("Access-Control-Allow-Methods", "HEAD, GET, POST, PUT, DELETE");
			response.addHeader("Access-Control-Allow-Headers",
					"Authorization, Origin, Content-Type, Accept, x-requested-with");
			response.addHeader("Access-Control-Max-Age", "3600");
		}
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}
}