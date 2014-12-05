package org.transgalactica.management.data.orm.context;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@Configuration
@Profile("test")
public class TestDatasourceConfig {

	@Bean(name = "dataSource", destroyMethod = "shutdown")
	public DataSource testDataSource() {
		return new EmbeddedDatabaseBuilder()
				.addScript("classpath:org/transgalactica/management/data/orm/script/createDatabase.sql")
				.addScript("classpath:org/transgalactica/management/data/orm/script/createReferentielData.sql")
				.addScript("classpath:org/transgalactica/management/data/orm/script/createTestData.sql").build();
	}
}
