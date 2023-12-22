package org.technical.test.model.payload.response;

import org.technical.test.model.entity.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetTaskResponse {

    private Task task;

    private int codeError;
    private String description;
    private String message;
    private boolean error;
    
}
