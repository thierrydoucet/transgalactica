package org.transgalactica.batch.salaire;

import javax.sql.DataSource;

import org.springframework.batch.test.JobLauncherTestUtils;
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
				.addScript("classpath:META-INF/scripts/createDatabase.sql") //
				.addScript("classpath:META-INF/scripts/createReferentielData.sql") //
				.addScript("classpath:createTestData.sql") //
				.addScript("classpath:org/springframework/batch/core/schema-hsqldb.sql") //
				.build();
	}

	@Bean
	public JobLauncherTestUtils jobLauncherTestUtils() {
		return new JobLauncherTestUtils();
	}
}