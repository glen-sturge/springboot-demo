package com.glensoft.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ApplicationConfig {
    @Bean
    @Qualifier("bean1")
    @Profile("dev")
    public MyFirstClass myFirstBean() {
        return new MyFirstClass("First Bean");
    }
    @Bean
    @Qualifier("bean2")
    @Profile("test")
    public MyFirstClass mySecondBean() {
        return new MyFirstClass("Second Bean");
    }
    @Bean
    @Qualifier("bean3")
    public MyFirstClass myThirdBean() {
        return new MyFirstClass("Third Bean");
    }
}
