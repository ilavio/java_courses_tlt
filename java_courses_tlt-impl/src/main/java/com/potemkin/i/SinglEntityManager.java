package com.potemkin.i;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Класс фабрика SinglEntityManager для EntityManagerFactory
 * 
 * @author Илья Пот
 *
 */
public class SinglEntityManager {
    private static EntityManagerFactory entityManagerFactory;
    
    private SinglEntityManager(){}
    
    public static EntityManagerFactory getEntityManagerFactory(){
    if(entityManagerFactory == null){
        entityManagerFactory = Persistence.createEntityManagerFactory("JPA-First");
    }
    return entityManagerFactory;
}
}
