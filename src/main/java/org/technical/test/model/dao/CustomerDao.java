package org.technical.test.model.dao;

import java.util.List;

import org.technical.test.model.entity.Customer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerDao implements PanacheRepository<Customer>{
    public List<Customer> findByName( String name ){
        return list("name", name);
    }
}
