package com.demo1.deepjha.demo.configuration;

import com.demo1.deepjha.demo.DB;
import com.demo1.deepjha.demo.DevDB;
import com.demo1.deepjha.demo.ProdDB;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    @ConditionalOnProperty(name="project.mode", havingValue ="development")
    public DB getDevDB() {
        return new DevDB();
    }

    @Bean
    @ConditionalOnProperty(name="project.mode", havingValue = "production")
    public DB getProdDB() {
        return new ProdDB();
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
