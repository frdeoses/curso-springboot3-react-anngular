package com.fran.curso.springboot.di.app.springboot.di;

import com.fran.curso.springboot.di.app.springboot.di.repositories.IProductRepository;
import com.fran.curso.springboot.di.app.springboot.di.repositories.ProductRepositoryJson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {


    @Value("classpath:json/product.json")
    private Resource resource;

    //    @Primary
    @Bean("productJson")
    IProductRepository productRepositoryJson() {
        return new ProductRepositoryJson(resource);
    }
}
