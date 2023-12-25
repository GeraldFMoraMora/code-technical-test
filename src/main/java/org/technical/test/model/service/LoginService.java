package org.technical.test.model.service;

import java.security.KeyPair;
import java.util.Base64;

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

    @Inject
    RSAEncryptService encryptService;

    public LoginCustomerResponse loginCustomer(Customer customer) throws Exception{

        Customer customerTemp = customerDao.findByName(customer.getName());
        LoginCustomerResponse customerResponse = new LoginCustomerResponse();
        if (customerTemp!=null){//Verifico si usuario existe
            UserKey userKey = keyDao.findByCustomerId(customerTemp.getId());
            if (userKey != null){//Verifico si extraje bien sus claves
                KeyPair keyPair = encryptService.retrieveFromDatabase(userKey.getPublic64_key(), userKey.getPrivate64_key());
                String passTemp = encryptService.decrypt(Base64.getDecoder().decode(customerTemp.getPassword()), keyPair.getPrivate());

                if(passTemp.equals(customer.getPassword())){
                    customerResponse.setCustomer(customerTemp);
                    customerResponse.setToken(userKey.getAnti_csrf_key());
                    customerResponse.setError(false);
                }else{
                    customerResponse.setCodeError(404);  
                    customerResponse.setDescription("User entered an incorrect password");
                    customerResponse.setMessage("ERROR: Password incorrect");  
                    customerResponse.setError(true);

                }
                
            }else{
                customerResponse.setCodeError(403);  
                customerResponse.setDescription("That user not have keys");
                customerResponse.setMessage("ERROR: keys does not exits");  
                customerResponse.setError(true);
            }
            
        }else{
            customerResponse.setCodeError(402);  
            customerResponse.setDescription("We have not found that username");
            customerResponse.setMessage("ERROR: Username not exist");  
            customerResponse.setError(true);
        }

        return customerResponse;
    }
    
}
