package com.restdesign.springboot.configs;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {
	
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.jpa.hibernate.default_schema}")
	private String schema;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;
    
    @Bean(name = "flywayStarter")
    public FlywayMigrationInitializer flywayMigrationInitializer() {
    	Flyway flyway = Flyway.configure()
    			.locations("")
    			.dataSource(url, user, password)
    			.baselineOnMigrate(true)
    			.baselineVersion("1")
    			.schemas(schema)
    			.load();
    	return new FlywayMigrationInitializer(flyway);
    	
    }
}
