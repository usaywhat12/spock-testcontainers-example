package com.karq.integtest.integtestframework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
public class PostgresqlConfig {


    @Bean
    public PostgreSQLContainer mssqlServer(){
        PostgreSQLContainer serverContainer =  new PostgreSQLContainer();
        serverContainer.start();
        System.setProperty("DB_URL", serverContainer.getJdbcUrl());
        System.setProperty("DB_USERNAME", serverContainer.getUsername());
        System.setProperty("DB_PASSWORD", serverContainer.getPassword());
        return serverContainer;
    }



}
