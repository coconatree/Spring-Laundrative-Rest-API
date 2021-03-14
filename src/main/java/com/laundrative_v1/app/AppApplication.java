package com.laundrative_v1.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.laundrative_v1.app.repository")
@EntityScan("com.laundrative_v1.app.entity")
public class AppApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AppApplication.class, args);
    }
}
