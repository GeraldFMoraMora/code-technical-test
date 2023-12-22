package org.technical.test.model.service;

import org.technical.test.model.dao.CustomerDao;
import org.technical.test.model.dao.UserKeyDao;
import org.technical.test.model.entity.Customer;
import org.technical.test.model.entity.UserKey;
import org.technical.test.model.payload.response.LoginCustomerResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LoginService {

    @Inject
    CustomerDao customerDao;

    @Inject
    UserKeyDao keyDao;

    public LoginCustomerResponse loginCustomer(Customer customer) throws Exception{
        Customer customerTemp = customerDao.findByName(customer.getName());
        LoginCustomerResponse customerResponse = new LoginCustomerResponse();
        if (customerTemp!=null){
            UserKey userKey = keyDao.findByCustomerId(customerTemp.getId());
            if (userKey != null){
                System.out.println(userKey.getId());
                customerResponse.setCustomer(customerTemp);
                customerResponse.setToken(userKey.getAnti_csrf_key());
                customerResponse.setError(false);
            }else{
                customerResponse.setCodeError(403);  
                customerResponse.setDescription("That user not have CSRF key");
                customerResponse.setMessage("ERROR: CSRF key does not exits");  
                customerResponse.setError(true);
            }
            
        }else{
            customerResponse.setCodeError(402);  
            customerResponse.setDescription("We have not find that username");
            customerResponse.setMessage("ERROR: Username not exist");  
            customerResponse.setError(true);
        }

        return customerResponse;
    }
    
}
