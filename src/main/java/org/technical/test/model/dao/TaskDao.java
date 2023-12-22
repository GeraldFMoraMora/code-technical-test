package org.technical.test.model.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.vertx.core.net.impl.pool.Task;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TaskDao implements PanacheRepository<Task> {
    
}
