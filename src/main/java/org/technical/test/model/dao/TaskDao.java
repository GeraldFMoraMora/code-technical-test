package org.technical.test.model.dao;

import org.technical.test.model.entity.Task;
import org.technical.test.model.entity.UserKey;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TaskDao implements PanacheRepository<Task> {

    public Task findByCustomerId( Integer customerId){
        return find("customer.id", customerId).firstResult();
    }

    public Task findByDescription( String description){
        return find("description", description).firstResult();
    }

    public Task findByDescriptionAndActive(String description, boolean enabled){
        return find("description = :description and enabled = :enabled",
        Parameters.with("description", description).and("enabled", enabled))
        .firstResult();
    }
    
}
