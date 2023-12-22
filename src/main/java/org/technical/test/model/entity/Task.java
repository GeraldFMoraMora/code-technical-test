package org.technical.test.model.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Table;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Getter
@Setter
@Entity
@Table(name = "task")
@RegisterForReflection
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String description;
    public String state;
    public String image_url;
    public boolean enabled;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Task(){
    }

    public boolean getEnabled(){return this.enabled;}

}
