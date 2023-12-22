package org.technical.test.model.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddTaskRequest {

    private String description;
    private String state;
    private boolean active;
    private String image_url;
    private Integer customer_id;
    
}
