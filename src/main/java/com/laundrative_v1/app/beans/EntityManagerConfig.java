package com.laundrative_v1.app.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class EntityManagerConfig
{
    @PersistenceContext
    private EntityManager em;

    public void setNaming()
    {
        System.out.println(em.getProperties());
    }
}
