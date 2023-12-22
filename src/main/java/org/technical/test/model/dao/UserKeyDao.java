package org.technical.test.model.dao;

import java.util.List;

import org.technical.test.model.entity.UserKey;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserKeyDao implements PanacheRepository<UserKey>{
    public List<UserKey> findById( Integer id){
        return list("id", id);
    }
}
