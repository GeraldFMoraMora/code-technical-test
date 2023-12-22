package org.technical.test.model.service;

import org.technical.test.global.ErrorDetails;
import org.technical.test.model.dao.CustomerDao;
import org.technical.test.model.entity.Customer;
import org.technical.test.model.payload.response.RegisterCustomerResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class RegisterService {

    @Inject
    CustomerDao customerDao;

    @Inject
    private RSAEncryptService encryptService;

    public RegisterCustomerResponse registerCustomer(Customer customer) throws Exception{
        Customer customerTemp = customerDao.findByName(customer.getName());
        RegisterCustomerResponse customerResponse = new RegisterCustomerResponse();

        if (customerTemp==null){

            customerDao.persist(encryptService.encriptPassword(customer));
            customerResponse.setCustomer(customer);
            customerResponse.setError(false);
        }else{
            ErrorDetails customerResponses = new ErrorDetails();
            customerResponse.setCodeError(401);  
            customerResponse.setDescription("Duplicated username");
            customerResponse.setMessage("ERROR: Username not avaible");  
            customerResponse.setError(true);
        }
        return customerResponse;
    }
    
}
