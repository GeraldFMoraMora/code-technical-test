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

import org.technical.test.global.ErrorCode;

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
                    customerResponse.setCodeError(ErrorCode.ERROR_102);  
                    customerResponse.setDescription(ErrorCode.ERROR_102_DESC);
                    customerResponse.setMessage(ErrorCode.ERROR_102_MSG);  
                    customerResponse.setError(true);

                }
                
            }else{
                customerResponse.setCodeError(ErrorCode.ERROR_101);  
                customerResponse.setDescription(ErrorCode.ERROR_101_DESC);
                customerResponse.setMessage(ErrorCode.ERROR_101_MSG);  
                customerResponse.setError(true);
            }
            
        }else{
            customerResponse.setCodeError(ErrorCode.ERROR_100);  
            customerResponse.setDescription(ErrorCode.ERROR_100_DESC);
            customerResponse.setMessage(ErrorCode.ERROR_100_MSG);  
            customerResponse.setError(true);
        }

        return customerResponse;
    }
    
}
