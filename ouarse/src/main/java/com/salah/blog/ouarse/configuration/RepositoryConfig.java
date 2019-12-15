package com.salah.blog.ouarse.configuration;

import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.hibernate.engine.transaction.jta.platform.internal.AtomikosJtaPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import com.salah.blog.ouarse.repository.PostRepository;
import com.salah.blog.ouarse.repository.UserRepository;
import com.salah.blog.ouarse.utils.ApplicationDatasourceProperties;

/**
 * Classe de configuration de la partie transactionnel des repositries
 * {@link UserRepository} et {@link PostRepository}.
 * 
 * @author salah
 * 
 */
@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "com.salah.blog.ouarse.repository", entityManagerFactoryRef = "ouarseEntityManager", transactionManagerRef = "transactionManager")
public class RepositoryConfig {

	/**
	 * 
	 * @param datasourceProperties
	 * @return
	 * @throws SQLException
	 */
	@Bean(name = "ouarseDataSource", initMethod = "init", destroyMethod = "close")
	@Profile("prod")
	public DataSource configDataSource(@Autowired ApplicationDatasourceProperties datasourceProperties) throws SQLException {
		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		mysqlXaDataSource.setUrl(datasourceProperties.getUrl());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXaDataSource.setPassword(datasourceProperties.getPassword());
		mysqlXaDataSource.setUser(datasourceProperties.getUsername());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlXaDataSource);
		xaDataSource.setUniqueResourceName("xads2");
		return xaDataSource;
	}

	/**
	 * 
	 * @param dataSource
	 * @return
	 */
	@Bean(name = "ouarseEntityManager")
	public LocalContainerEntityManagerFactoryBean configEntityManager(
			@Qualifier("ouarseDataSource") DataSource dataSource,
			@Autowired JpaVendorAdapter jpaVendorAdapter) {

		HashMap<String, String> properties = new HashMap<>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(dataSource);
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan("com.salah.blog.ouarse.entities");
		entityManager.setPersistenceUnitName("ouarsePersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

	/**
	 * 
	 * @param datasourceProperties
	 * @return
	 */
	@Bean(name = "ouarseDataSource")
	@Profile("test")
	public DataSource configMockDataSource(@Autowired ApplicationDatasourceProperties datasourceProperties) {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.h2.Driver");
		dataSourceBuilder.url(datasourceProperties.getUrl());
		dataSourceBuilder.username(datasourceProperties.getUsername());
		dataSourceBuilder.password(datasourceProperties.getPassword());
		return dataSourceBuilder.build();
	}

}
