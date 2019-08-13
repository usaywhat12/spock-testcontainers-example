package com.karq.integtest.integtestframework.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
public class FlywayConfig {

    @Bean
    public Flyway flyway(PostgreSQLContainer postgreSQLContainer) {
        Flyway flyway = Flyway.configure()
                .dataSource(postgreSQLContainer.getJdbcUrl(), postgreSQLContainer.getUsername(), postgreSQLContainer.getPassword())
                .locations("db/migration")
                .load();

        flyway.migrate();
        return flyway;
    }

}
