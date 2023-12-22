package org.technical.test.model.dao;

import org.technical.test.model.entity.Customer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerDao implements PanacheRepository<Customer>{

    public Customer findByName(String name) {
        return find("name", name).firstResult();
    }

    public Customer findById(Integer id) {
        return find("id", id).firstResult();
    }
}
