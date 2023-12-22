package org.technical.test.model.payload.response;

import io.vertx.core.net.impl.pool.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskResponse {

    private Task task;

    private int codeError;
    private String description;
    private String message;
    private boolean error;
    
}
