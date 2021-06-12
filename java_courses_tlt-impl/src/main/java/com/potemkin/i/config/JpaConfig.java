package com.potemkin.i.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Конфигурационный класс JpaConfig создания соединения с базой данных через
 * EntityManagerFactory
 * 
 * @author Илья Пот
 *
 */
@Profile("!local")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.potemkin.i.repository" })
public class JpaConfig {

    @Profile("!local")
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean entityManagerFactory = new LocalEntityManagerFactoryBean();
        entityManagerFactory.setPersistenceUnitName("JPA-First");

        return entityManagerFactory;
    }

    @Profile("!local")
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }
}
