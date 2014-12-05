package org.transgalactica.management.business.logistics.service.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.transgalactica.management.business.logistics.service.HangarService;
import org.transgalactica.management.business.logistics.service.VaisseauService;

@Configuration
public class SecurityRolesMockConfig {

	@Bean
	public HangarService noOperationHangarService() {
		return new NoOperationHangarService();
	}

	@Bean
	public VaisseauService noOperationVaisseauService() {
		return new NoOperationVaisseauService();
	}
}
