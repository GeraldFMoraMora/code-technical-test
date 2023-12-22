package org.technical.test.model.payload.response;

import java.util.List;

import org.technical.test.model.entity.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetListTaskResponse {

    private List<Task> task;

    private int codeError;
    private String description;
    private String message;
    private boolean error;
    
}
