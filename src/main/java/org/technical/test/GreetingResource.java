package org.technical.test;

import org.technical.test.model.service.RSAEncryptService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() throws Exception {
        RSAEncryptService rsaEncryptService = new RSAEncryptService();
        rsaEncryptService.testCifrado("Hola soy Gerald Mora Mora");
        return "Hello RESTEasy";
    }
}
