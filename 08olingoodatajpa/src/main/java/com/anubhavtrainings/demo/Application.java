package com.anubhavtrainings.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import com.anubhavtrainings.demo.entities.AddressODataAgent;
import com.anubhavtrainings.demo.entities.VendorODataAgent;
import com.anubhavtrainings.demo.processor.MyODataServiceFactory;

@SpringBootApplication(scanBasePackages = "com.anubhavtrainings.demo")
@EnableJpaRepositories(basePackages = "com.anubhavtrainings.demo")
@EntityScan(basePackages = "com.anubhavtrainings.demo") 
@ServletComponentScan(basePackages = "com.anubhavtrainings.demo")
@EnableAsync

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean(name="com.anubhavtrainings.demo.processor.MyODataServiceFactory")
	public ODataServiceFactory getServiceFactory(){
		return new com.anubhavtrainings.demo.processor.MyODataServiceFactory("com.anubhavtrainings.demo");
	}
	
	@Bean(name="com.anubhavtrainings.demo.entities.VendorODataAgent")
	public VendorODataAgent vendorODataAgent(){
		return new VendorODataAgent();
	}
	
	@Bean(name="com.anubhavtrainings.demo.entities.AddressODataAgent")
	public AddressODataAgent addressODataAgent(){
		return new AddressODataAgent();
	}


}
