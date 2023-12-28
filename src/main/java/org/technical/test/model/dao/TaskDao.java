package org.technical.test.model.dao;

import java.util.List;

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

    public Task findByDescriptionAndCustomer(String description, Integer customerId) {
        return find("description = ?1 and customer.id = ?2",
                description, customerId)
                .firstResult();
    }

    public Task findByIdAndCustomer(Integer id, Integer customerId) {
        return find("id = ?1 and customer.id = ?2",
                id, customerId)
                .firstResult();
    }

    public List<Task> findListByCustomer(Integer customerId) {
        return list("customer.id = ?1", customerId);
    }
    public List<Task> findListByCustomerAndEnabled(Integer customerId, Boolean enabled) {
        return list("customer.id = ?1 and enabled = ?2", customerId, enabled);
    }
    public List<Task> findListByCustomerAndState(Integer customerId, String state) {
        return list("customer.id = ?1 and state = ?2", customerId, state);
    }

    public Task findByDescriptionAndActive(String description, boolean enabled){
        return find("description = :description and enabled = :enabled",
        Parameters.with("description", description).and("enabled", enabled))
        .firstResult();
    }

    public Task findByDescriptionAndStatusAndCustomer(String description, boolean enabled, Integer customerId) {
        return find("description = ?1 and enabled = ?2 and customer.id = ?3",
                description, enabled, customerId)
                .firstResult();
    }
    
}
