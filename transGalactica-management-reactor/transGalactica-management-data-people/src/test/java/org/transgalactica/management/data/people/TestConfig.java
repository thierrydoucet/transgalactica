package org.transgalactica.management.data.people;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
@ComponentScan({ "org.transgalactica.management.data.referentiel", "org.transgalactica.management.data.materiel",
		"org.transgalactica.management.data.people" })
@EnableJpaRepositories({ "org.transgalactica.management.data.referentiel.dao",
		"org.transgalactica.management.data.materiel.dao", "org.transgalactica.management.data.people.dao" })
public class TestConfig {

	@Bean(name = "dataSource", destroyMethod = "shutdown")
	public DataSource testDataSource() {
		return new EmbeddedDatabaseBuilder() //
				.addScript("classpath:createDatabase.sql") //
				.addScript("classpath:createReferentielData.sql")//
				.addScript("classpath:createTestData.sql") //
				.build();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory());
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setPersistenceUnitName("transGalacticaTest-PUnit");
		entityManager.setDataSource(testDataSource());
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
		jpaVendorAdapter.setShowSql(true);
		return jpaVendorAdapter;
	}
}
