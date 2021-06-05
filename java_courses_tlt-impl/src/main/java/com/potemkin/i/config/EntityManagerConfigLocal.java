package com.potemkin.i.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local")
@Configuration
public class EntityManagerConfigLocal {
}
