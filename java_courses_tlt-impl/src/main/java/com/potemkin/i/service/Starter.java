package com.potemkin.i.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component("starter")
public class Starter {
    @Autowired
    ServiceCrud service;
    
    public void serviceStart(AnnotationConfigApplicationContext context) {
        service.start(context);
    }
}
