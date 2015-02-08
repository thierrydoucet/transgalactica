package org.transgalactica.management.ws;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@Configuration
@Profile("test")
public class TestConfig {

	@Bean(name = "dataSource", destroyMethod = "shutdown")
	public DataSource testDataSource() {
		return new EmbeddedDatabaseBuilder() //
				.addScript("classpath:script/createDatabase.sql") //
				.addScript("classpath:createTestData.sql") //
				.build();
	}
}