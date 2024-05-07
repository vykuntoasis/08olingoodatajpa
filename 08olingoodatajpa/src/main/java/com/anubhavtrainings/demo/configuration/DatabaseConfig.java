package com.anubhavtrainings.demo.configuration;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.relational.DataSourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.anubhavtrainings.demo.entities.Vendor;
import com.zaxxer.hikari.HikariDataSource;


//@Profile({"local", "cf-test", "cf-dev","cf-prod"})
@Configuration
//@Profile("cloud")
public class DatabaseConfig extends AbstractCloudConfig {


    /**
     * (Step 1) Parses the local environment variable VCAP_SERVICES (containing
     * cloud information) and provides a DataSource. The superclass
     * {@link AbstractCloudConfig}, part of the Spring Cloud plugin, is used for
     * this.
     */
	//
	Logger cloudFoundryDataSourceConfigLogger = LoggerFactory.getLogger(this.getClass());


	@Value("${vcap.services.hanaschema.credentials.user}")
	private String username;

	@Value("${vcap.services.hanaschema.credentials.password}")
	private String password;
	
	@Value("${vcap.services.hanaschema.credentials.url}")
	private String hostname;
	
	@Value("${vcap.services.hanaschema.credentials.port}")
	private String port;
	
	@Value("${vcap.services.hanaschema.credentials.schema}")
	private String schemaname;	
	
    @Bean
    public DataSource dataSource() {
        /*
         * Load BasicDbcpPooledDataSourceCreator before
         * TomcatJdbcPooledDataSourceCreator. Also see the following link for a
         * detailled discussion of this issue:
         * https://stackoverflow.com/questions/36885891/jpa-eclipselink-understanding-
         * classloader-issues
         */
        List<String> dataSourceNames = Arrays.asList("BasicDbcpPooledDataSourceCreator",
                "TomcatJdbcPooledDataSourceCreator", "HikariCpPooledDataSourceCreator",
                "TomcatDbcpPooledDataSourceCreator");
        
        DataSourceConfig dbConfig = new DataSourceConfig(dataSourceNames);
        //DataSource hikariDataSource =  connectionFactory().dataSource(dbConfig);
        DataSource myConnection = DataSourceBuilder.create()
        						  .type(HikariDataSource.class)
        						  .driverClassName(com.sap.db.jdbc.Driver.class.getName())
        						  .url(hostname)
        						  .username(username)
        						  .password(password)
        						  .build();
        
        try {
			myConnection.getConnection().setSchema(schemaname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        cloudFoundryDataSourceConfigLogger.info("Detected Host name is : " + this.hostname);
        cloudFoundryDataSourceConfigLogger.info("Detected port name is : " + this.port);
        cloudFoundryDataSourceConfigLogger.info("Detected Schema name is : " + this.schemaname);
        cloudFoundryDataSourceConfigLogger.info("Detected User name is : " + this.username);
        
        return myConnection;
        
    }

    /**
     * (Step 2a) Based on a {@link DataSource} (provided using the method above),
     * provides a factory to create {@link javax.persistence.EntityManager}
     * instances (a core class of JPA). Also see
     * {@link EntityManagerFactoryProvider}.
     */
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        return EntityManagerFactoryProvider.get(dataSource, Vendor.class.getPackage().getName());
    }
//	Above code is commented if required need to be uncommented

    /**
     * (Step 2b) Based on an {@link EntityManagerFactory} (provided using the method
     * above), provides a {@link JpaTransactionManager} (another core class of JPA).
     */
    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
