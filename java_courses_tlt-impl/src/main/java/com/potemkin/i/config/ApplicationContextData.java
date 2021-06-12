package com.potemkin.i.config;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@ComponentScan("com.potemkin.i")
@RequiredArgsConstructor
@EnableWebMvc
public class ApplicationContextData implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;

    /**
     * Метод получение messageSource
     *
     * @return MessageSource object
     */
    @Profile("local")
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
        reloadableResourceBundleMessageSource.setBasename("classpath:messages/message");
        return reloadableResourceBundleMessageSource;
    }

    /**
     * Метод получения locale
     *
     * @return Locale object
     */
    @Profile("local")
    @Bean
    public Locale locale() {
        return new Locale("ru", "RU");
    }
}
