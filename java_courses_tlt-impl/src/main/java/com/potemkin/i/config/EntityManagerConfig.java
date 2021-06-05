package com.potemkin.i.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("!local")
@Configuration
@ComponentScan("com.potemkin.i")
@PropertySource("classpath:entity.properties")
public class EntityManagerConfig {
    
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("JPA-First");
    }
}
