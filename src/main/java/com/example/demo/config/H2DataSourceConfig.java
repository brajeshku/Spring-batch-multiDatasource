//package com.example.demo.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class H2DataSourceConfig {
//
//    @Bean(name = "batchDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.h2")
//    public DataSource batchDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//}