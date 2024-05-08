package com.fran.curso.springboot.jpa.springbootjparelation.repository;

import com.fran.curso.springboot.jpa.springbootjparelation.entities.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
