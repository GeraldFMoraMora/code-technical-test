package org.technical.test.api;

import java.util.List;

import org.technical.test.model.dao.CustomerDao;
import org.technical.test.model.entity.Customer;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/register")
public class CustomerResource {

    @Inject
    CustomerDao customerDao;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Customer registerCustomer(Customer customer){
        customerDao.persist(customer);
        return customer;
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers(@PathParam("name") String name){
        List<Customer> customers = customerDao.findByName(name);
        return Response.ok(customers).build();
    }
    
}
