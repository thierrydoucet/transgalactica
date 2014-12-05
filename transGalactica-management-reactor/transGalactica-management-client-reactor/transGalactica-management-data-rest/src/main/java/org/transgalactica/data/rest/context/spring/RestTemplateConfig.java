package org.transgalactica.data.rest.context.spring;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.DefaultHttpClient;
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
		Credentials credentials = new UsernamePasswordCredentials(restUsername, restPassword);
		DefaultHttpClient client = new DefaultHttpClient();
		client.getCredentialsProvider().setCredentials(AuthScope.ANY, credentials);
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));
	}
}
