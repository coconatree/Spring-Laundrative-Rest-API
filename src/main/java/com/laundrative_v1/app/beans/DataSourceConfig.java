package com.laundrative_v1.app.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
    @author Emre Caniklioglu
    @versio 1.0.0

    This is the config class for setting up the connection between the
    mariadb and spring rest API
 */

@Configuration
public class DataSourceConfig
{
    @Primary
    @Bean
    public DataSource datasource()
    {
        /**
            Creates and returns a DataSource wtih the following properties;
            Driver class name: org.mariadb.jdbc.Driver
            Url: org.mariadb.jdbc.Driver
            Username: root
            Password: 191200

            Username and the password will set as environment variables in the future
         */

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mariadb://localhost:3306/laundrative");
        dataSource.setUsername("root");
        dataSource.setPassword("191200");

        return dataSource;
    }
}
