package org.technical.test.model.dao;

import org.technical.test.model.entity.UserKey;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserKeyDao implements PanacheRepository<UserKey>{

    public UserKey findByCustomerId( Integer customerId){
        return find("customer.id", customerId).firstResult();
    }
}
