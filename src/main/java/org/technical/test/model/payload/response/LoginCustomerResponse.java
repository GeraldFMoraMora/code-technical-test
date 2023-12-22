package org.technical.test.model.payload.response;

import org.technical.test.model.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginCustomerResponse {

    private Customer customer;
    private String token;

    private int codeError;
    private String description;
    private String message;
    private boolean error;
    
}
