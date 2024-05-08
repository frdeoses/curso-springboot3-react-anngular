package com.fran.curso.springboot.jpa.springbootjpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@Embeddable
public class Audit {
    @Column(name = "create_at")
    private LocalDateTime createAT;

    @Column(name = "updated_at")
    private LocalDateTime updatedAT;

    @PrePersist
    public void prePersist() {

        System.out.println(
                "Evento del ciclo de vida del entity prePersist..."
        );
        this.createAT = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {

        System.out.println(
                "Evento del ciclo de vida del entity preUpdate..."
        );
        this.updatedAT = LocalDateTime.now();
    }

    public LocalDateTime getCreateAT() {
        return createAT;
    }

    public void setCreateAT(LocalDateTime createAT) {
        this.createAT = createAT;
    }

    public LocalDateTime getUpdatedAT() {
        return updatedAT;
    }

    public void setUpdatedAT(LocalDateTime updatedAT) {
        this.updatedAT = updatedAT;
    }
}
