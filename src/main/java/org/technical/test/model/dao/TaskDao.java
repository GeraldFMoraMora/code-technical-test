package org.technical.test.model.dao;

import org.technical.test.model.entity.Task;

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

    /**public Task findByDescriptionAndCustomerId(String description, Integer customerId) {
        return find("description = :description and customer.id = :customerId",
                Parameters.with("description", description).and("customerId", customerId))
                .firstResult();
    }*/
    public Task findByDescriptionAndCustomer(String description, Integer customerId) {
        return find("description = ?1 and customer.id = ?2",
                description, customerId)
                .firstResult();
    }

    public Task findByDescriptionAndActive(String description, boolean enabled){
        return find("description = :description and enabled = :enabled",
        Parameters.with("description", description).and("enabled", enabled))
        .firstResult();
    }
    /**public Task findByDescriptionAndCustomerIdAndActive( String description, Integer customerId, boolean enabled){
        return find("description = :description and customer_id = :customerId and enabled = :enabled",
        Parameters.with("description", description).and("customerId", customerId)
        .and("enabled", enabled))
        .firstResult();
    }*/
    public Task findByDescriptionAndStatusAndCustomer(String description, boolean enabled, Integer customerId) {
        return find("description = ?1 and enabled = ?2 and customer.id = ?3",
                description, enabled, customerId)
                .firstResult();
    }
    
}
