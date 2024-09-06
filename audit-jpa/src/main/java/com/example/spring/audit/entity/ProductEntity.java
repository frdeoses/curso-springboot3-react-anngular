package com.example.spring.audit.entity;

import com.example.spring.audit.listener.AuditProductListener;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@EntityListeners(AuditProductListener.class)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private BigDecimal price;

//    private String operation;
//
//    @Column(name = "date_event")
//    private LocalDateTime dateEvent;
//
//    @PrePersist
//    public void onPrePersist() {
//        audit("INSERT");
//    }
//
//    @PreUpdate
//    public void onPreUpdate() {
//        audit("UPDATE");
//    }
//
//
//    public void audit(String operation) {
//        setOperation(operation);
//        setDateEvent(LocalDateTime.now());
//    }

}
