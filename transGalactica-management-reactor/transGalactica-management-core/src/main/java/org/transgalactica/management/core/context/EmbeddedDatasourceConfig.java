package org.transgalactica.management.core.context;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@Configuration
@Profile("embedded")
public class EmbeddedDatasourceConfig {

	@Bean(name = "dataSource", destroyMethod = "shutdown")
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setName("Trans'Galactiva embedded db")
				.addScript("classpath:org/transgalactica/management/core/script/createDatabase.sql")
				.addScript("classpath:org/transgalactica/management/core/script/createReferentielData.sql")
				.addScript("classpath:org/transgalactica/management/core/script/createSampleData.sql").build();
	}
}
