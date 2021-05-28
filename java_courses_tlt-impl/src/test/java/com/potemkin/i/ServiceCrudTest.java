package com.potemkin.i;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.potemkin.i.config.SpringConfigLocal;
import com.potemkin.i.config.SpringConfigMy;
import com.potemkin.i.service.ServiceCrud;
import com.potemkin.i.service.ServiceLocal;
import com.potemkin.i.service.Starter;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ServiceLocal.class)
@ActiveProfiles("local")
public class ServiceCrudTest {
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    private ServiceCrud serviceCrud = new ServiceCrud();
    private EntityManagerFactory entityManagerFactoryMock;
    private EntityManager entityManagerMock;
    private EntityTransaction entityTransactionMock;
    private SpringConfigLocal springConfigLocal = new SpringConfigLocal();
    private CrudHandlerSupAndProduct crudSupAndProd;
    private CrudHandler crudCustAndOrd;

    @BeforeEach
    public void maskingObjects() {
        context.getEnvironment().setActiveProfiles("local");
        context.scan("com.potemkin.i");
        context.refresh();
        entityManagerFactoryMock = mock(EntityManagerFactory.class);
        entityManagerMock = mock(EntityManager.class);
        entityTransactionMock = mock(EntityTransaction.class);
        SpringConfigMy configMy = mock(SpringConfigMy.class);

        when(entityManagerFactoryMock.createEntityManager()).thenReturn(entityManagerMock);
        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
        when(configMy.entityManagerFactory()).thenReturn(entityManagerFactoryMock);

        crudSupAndProd = new CrudHandlerSupAndProduct(entityManagerFactoryMock);
        crudCustAndOrd = new CrudHandler(entityManagerFactoryMock);
    }

    @Test
    public void testStart() {
        var starter = context.getBean("starter", Starter.class);
        starter.serviceStart(context);
        context.close();
    }
}
