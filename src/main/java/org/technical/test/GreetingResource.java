package org.technical.test;

import org.technical.test.model.dao.CustomerDao;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        CustomerDao customerDao = new CustomerDao();
        //customerDao.test();
        return "Hello RESTEasy";
    }
}
