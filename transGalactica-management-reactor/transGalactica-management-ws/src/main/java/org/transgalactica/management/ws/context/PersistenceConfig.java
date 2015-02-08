package org.transgalactica.management.ws.context;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("org.transgalactica.management.data.materiel.dao")
public class PersistenceConfig {

	@Autowired
	private Environment env;

	@Autowired
	private DataSource dataSource;

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory());
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setPersistenceUnitName("transGalactica-PUnit");
		entityManager.setDataSource(dataSource);
		entityManager.setPackagesToScan("org.transgalactica.management.data.*.bo");

		entityManager.setJpaVendorAdapter(jpaVendorAdapter());
		entityManager.setJpaDialect(new HibernateJpaDialect());

		entityManager.afterPropertiesSet();

		return entityManager.getNativeEntityManagerFactory();
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.HSQL);
		jpaVendorAdapter.setShowSql(env.getProperty("test.showSql", boolean.class, false));
		return jpaVendorAdapter;
	}

	@Configuration
	@Profile("embedded")
	public static class EmbeddedDatasourceConfig {

		@Bean(name = "dataSource", destroyMethod = "shutdown")
		public DataSource dataSource() {
			return new EmbeddedDatabaseBuilder().setName("Trans'Galactiva embedded db")
					.addScript("classpath:script/createDatabase.sql")
					.addScript("classpath:script/createSampleData.sql").build();
		}
	}
}
