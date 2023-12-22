package org.technical.test.model.payload.response;

import org.technical.test.model.entity.Customer;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCustomerResponse {
    
    private Customer customer;

    private int codeError;
    private String description;
    private String message;
    private boolean error;

}
