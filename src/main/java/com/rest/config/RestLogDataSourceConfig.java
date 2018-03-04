package com.rest.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "logEntityManagerFactory",
  transactionManagerRef="logTransactionManager",
  basePackages = { "com.rest.log.repository" }
)
public class RestLogDataSourceConfig {
	
	@Bean(value = "logDataSource")
	@ConfigurationProperties(prefix="datasource.log")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	  
	@Bean(value="logEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("logDataSource") DataSource dataSource
	) {
	  return builder
			  .dataSource(dataSource)
			  .packages("com.rest.log.domain")
			  .persistenceUnit("rest_log")
			  .build();
	}
	
	@Bean(value="logTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("logEntityManagerFactory") EntityManagerFactory entityManagerFactory
	) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
