package com.fran.curso.springboot.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:messages.properties")
public class AppConfig {

}
