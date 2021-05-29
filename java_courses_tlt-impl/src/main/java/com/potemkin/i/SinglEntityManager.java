package com.potemkin.i;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
