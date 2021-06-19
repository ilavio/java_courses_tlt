package com.potemkin.i;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnProperty(prefix = "log", name = "enabled", havingValue = "true")
public class ConfigLog {
    
    @Bean
    public LoggingAOP loggingAOP() {
        var logging = new LoggingAOP();
        return logging;
    }
}
