package org.technical.test.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    public Integer id;
    public String name;
    public String password;
    public Integer telephone;
    public String ageRange;
    public Integer gender;
    public boolean isActive;

    public User(){
        this.isActive = true;
    }
    
}
