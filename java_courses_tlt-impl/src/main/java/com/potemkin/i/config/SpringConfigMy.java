package com.potemkin.i.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.potemkin.i.SinglEntityManager;

@Profile("!local")
@Configuration
@ComponentScan("com.potemkin.i")
@PropertySource("classpath:entity.properties")
public class SpringConfigMy implements Config{
    
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return SinglEntityManager.getEntityManagerFactory();
    }
}
