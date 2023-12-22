package org.technical.test.global;

import jakarta.json.bind.annotation.JsonbProperty;

//import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    @JsonbProperty("codeError")
    private int codeError;

    @JsonbProperty("description")
    private String description;

    @JsonbProperty("message")
    private String message;

    @JsonbProperty("error")
    private boolean error;
}
