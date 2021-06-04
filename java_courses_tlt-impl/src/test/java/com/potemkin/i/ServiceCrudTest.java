package com.potemkin.i;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.potemkin.i.config.EntityManagerConfig;
import com.potemkin.i.service.ServiceLocal;
import com.potemkin.i.service.Starter;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ServiceLocal.class)
@ActiveProfiles("local")
public class ServiceCrudTest {
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    private EntityManagerFactory entityManagerFactoryMock;
    private EntityManager entityManagerMock;
    private EntityTransaction entityTransactionMock;

    @BeforeEach
    public void maskingObjects() {
        context.getEnvironment().setActiveProfiles("local");
        context.scan("com.potemkin.i");
        context.refresh();
        entityManagerFactoryMock = mock(EntityManagerFactory.class);
        entityManagerMock = mock(EntityManager.class);
        entityTransactionMock = mock(EntityTransaction.class);
        EntityManagerConfig configMy = mock(EntityManagerConfig.class);

        when(entityManagerFactoryMock.createEntityManager()).thenReturn(entityManagerMock);
        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
        when(configMy.entityManagerFactory()).thenReturn(entityManagerFactoryMock);
    }

    @Test
    public void testStart() {
        var starter = context.getBean("starter", Starter.class);
        starter.serviceStart(context);
        context.close();
    }
}
