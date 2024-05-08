package com.fran.curso.springboot.di.app.springboot.di.services;

import com.fran.curso.springboot.di.app.springboot.di.models.Product;
import com.fran.curso.springboot.di.app.springboot.di.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    //    @Autowired
//    @Qualifier("productFoo")

    //    @Autowired
//    private final Environment environment;

    @Value("${config.price.tax}")
    private Double tax;
    private final IProductRepository repository;

//    @Autowired
//    public void setRepository(IProductRepository repository) {
//        this.repository = repository;
//    }


//    public ProductServiceImpl(@Qualifier("productList") IProductRepository repository, Environment environment) {
//        this.environment = environment;
//        this.repository = repository;
//    }

    public ProductServiceImpl(@Qualifier("productJson") IProductRepository repository) {
        this.repository = repository;
    }
//    public ProductServiceImpl(IProductRepository repository) {
//        this.repository = repository;
//    }

    public List<Product> findAll() {
        return repository.findAll().stream()
                .map(product -> {
//                    Double priceImp = product.getPrice() * environment.getProperty("config.price.tax", Double.class);
                    Double priceImp = product.getPrice() * tax;
//                    System.out.println(environment.getProperty("config.price.tax", Double.class));
//                    System.out.println(tax);
//                    product.setPrice(priceImp.longValue());
//                    return product;

//                    Product newProduct = new Product(product.getId(), product.getName(), priceImp.longValue());

                    Product newProduct = (Product) product.clone();
                    newProduct.setPrice(priceImp.longValue());

                    return newProduct;
                })
                .collect(Collectors.toList());
    }

    public Product findById(Long id) {
        return repository.findById(id);
    }
}
