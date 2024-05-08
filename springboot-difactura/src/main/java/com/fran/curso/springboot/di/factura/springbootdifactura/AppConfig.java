package com.fran.curso.springboot.di.factura.springbootdifactura;

import com.fran.curso.springboot.di.factura.springbootdifactura.models.Item;
import com.fran.curso.springboot.di.factura.springbootdifactura.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource("classpath:data.properties")
public class AppConfig {


    @Bean
//    @Primary
    List<Item> itemsInvoice() {

        Product product = new Product("Sony", 800);
        Product product2 = new Product("Bicicleta", 500);

        return Arrays.asList(new Item(product, 2), new Item(product2, 4));

    }

    @Bean("default")
//    @Primary
    List<Item> itemsInvoiceOffice() {

        Product product = new Product("Monitor", 700);
        Product product2 = new Product("Noot book", 1500);
        Product product3 = new Product("Impreesora", 400);
        Product product4 = new Product("Escritorio", 2500);

        return Arrays.asList(new Item(product, 3),
                new Item(product2, 2)
                , new Item(product3, 1)
                , new Item(product4, 2));

    }
}
