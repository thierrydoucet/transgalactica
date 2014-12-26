package org.transgalactica.management.data.rest.context.spring;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Classe de configuration de la restTemplate
 * 
 * @author Thierry
 */
@Configuration
public class RestTemplateConfig {

	@Value("${rest.username}")
	private String restUsername;

	@Value("${rest.password}")
	private String restPassword;

	protected RestTemplateConfig() {
	}

	@Bean
	public RestTemplate getRestTemplate() {
		// credential
		Credentials credentials = new UsernamePasswordCredentials(restUsername, restPassword);
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, credentials);

		// accept header
		HttpRequestInterceptor acceptHeaderInterceptor = (request, context) -> request.addHeader("Accept",
				"application/xml");

		// build client
		HttpClient client = HttpClientBuilder.create() //
				.setDefaultCredentialsProvider(credentialsProvider) //
				.addInterceptorFirst(acceptHeaderInterceptor).build();
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));
	}
}
