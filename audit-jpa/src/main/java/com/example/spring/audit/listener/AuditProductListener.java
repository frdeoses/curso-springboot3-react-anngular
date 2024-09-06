package com.example.spring.audit.listener;

import com.example.spring.audit.entity.HistoryEntity;
import com.example.spring.audit.entity.ProductEntity;
import com.example.spring.audit.repository.HistoryRepository;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor(onConstructor_ = @__(@Lazy))
public class AuditProductListener {

    private final HistoryRepository repository;

//    una forma para solucionar dependeencia ciclica
//    @Lazy
//    public AuditProductListener(final HistoryRepository repository) {
//        this.repository = repository;
//    }

    @PrePersist
    public void prePersist(ProductEntity product) {

        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        HistoryEntity history = new HistoryEntity();
        history.setName(product.getName());
        history.setDate(LocalDateTime.now());
        history.setOperation("INSERT");
        history.setUser(user);
        repository.save(history);
    }

    @PreUpdate
    public void preUpdate(ProductEntity product) {

        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        HistoryEntity history = new HistoryEntity();
        history.setName(product.getName());
        history.setDate(LocalDateTime.now());
        history.setOperation("UPDATE");
        history.setUser(user);
        repository.save(history);
    }

    @PreRemove
    public void preRemove(ProductEntity product) {

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        HistoryEntity history = new HistoryEntity();
        history.setName(product.getName());
        history.setDate(LocalDateTime.now());
        history.setOperation("REMOVE");
        history.setUser(user);
        repository.save(history);
    }
}
