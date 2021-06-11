package com.potemkin.i.config;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.potemkin.i.converter.CustomerDtoToCustomer;
import com.potemkin.i.converter.CustomerToCustomerDTO;
import com.potemkin.i.converter.OrderDtoToOrder;
import com.potemkin.i.converter.OrderToOrderDTO;
import com.potemkin.i.converter.ProductDtoToProduct;
import com.potemkin.i.converter.ProductToProductDTO;
import com.potemkin.i.converter.SupplierDtoToSupplier;
import com.potemkin.i.converter.SupplierToSupplierDTO;

/**
 * Конфигурационный класс JpaConfig создания соединения с базой данных через
 * EntityManagerFactory и регистрация конвертеров для преоброзования
 * 
 * @author Илья Пот
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.potemkin.i.repository" })
public class JPAConfig {
    
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean entityManagerFactory = new LocalEntityManagerFactoryBean();
        entityManagerFactory.setPersistenceUnitName("JPA-First");

        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }
    
    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        Set<Converter>converters = new HashSet<>();
        converters.add(new CustomerDtoToCustomer());
        converters.add(new CustomerToCustomerDTO());
        converters.add(new SupplierToSupplierDTO());
        converters.add(new SupplierDtoToSupplier());
        converters.add(new ProductToProductDTO());
        converters.add(new OrderToOrderDTO());
        converters.add(new OrderDtoToOrder());
        converters.add(new ProductDtoToProduct());
        bean.setConverters(converters);
        return bean;
    }
}
