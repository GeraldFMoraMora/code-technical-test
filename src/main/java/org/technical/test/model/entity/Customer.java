package org.technical.test.model.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String name;
    public String password;
    public String telephone;
    public String age_range;
    public Integer gender;
    public boolean is_active;

    public Customer(){
        this.is_active = true;
    }
    
}
