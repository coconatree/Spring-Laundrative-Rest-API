package com.laundrative_v1.app.beans;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig
{
    @Bean
    public DataSource datasource()
    {
        return DataSourceBuilder.create()
                .driverClassName("org.mariadb.jdbc.Driver")
                .url("jdbc:mariadb://localhost:3306/laundrative") // These will be environment variables
                .username("root")
                .password("191200")
                .build();
    }
}
