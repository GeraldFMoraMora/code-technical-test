package org.technical.test.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

    public Integer id;
    public String name;
    public String password;
    public String telephone;
    public String ageRange;
    public Integer gender;
    public boolean isActive;

    public Customer(){
        this.isActive = true;
    }
    
}
