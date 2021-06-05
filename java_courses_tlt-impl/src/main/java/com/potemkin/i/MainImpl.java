package com.potemkin.i;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.potemkin.i.service.Starter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainImpl {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        log.info("---> Дефолтный профиль <---");
        context.scan("com.potemkin.i");
        context.refresh();
        var starter = context.getBean("starter", Starter.class);
        starter.serviceStart(context);

//       log.info("---> Активация профиля local <---");
//       context.getEnvironment().setActiveProfiles("local");
//       context.scan("com.potemkin.i");
//       context.refresh();
//       var starter2 = context.getBean("starter", Starter.class);
//       starter2.serviceStart(context);
//       context.close();
    }
}
