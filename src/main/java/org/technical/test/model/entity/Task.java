package org.technical.test.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task {

    public Integer id;
    public String description;
    public String state;
    public String imageUrl;
    public boolean isActive;

    public Task(){
        this.isActive = true;
    }

}
