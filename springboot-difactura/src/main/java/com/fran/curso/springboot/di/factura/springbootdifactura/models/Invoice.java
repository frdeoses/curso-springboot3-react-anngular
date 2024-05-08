package com.fran.curso.springboot.di.factura.springbootdifactura.models;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Component
@RequestScope // inyecta como una copia o proxy
//@JsonIgnoreProperties({"advisors", "targetSource"}) // evita los errores que te salen con el @ anterior
public class Invoice {

    @Autowired
    private Client client;

    @Value("${invoice.description}")
    private String description;

    @Autowired
    @Qualifier("default")
    private List<Item> items;

    @PostConstruct
    public void init() {

        System.out.println("Creando el componente dee la factura");
        client.setName(client.getName().concat(client.getLastname()));
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Deestruyendo el componente dee la factura");
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getTotal() {

//        int total = 0;
//        for (Item item : items){
//            total += item.getImport();
//
//        }
        return items.stream()
                .map(Item::getImport)
                .reduce(0, Integer::sum);

    }
}
