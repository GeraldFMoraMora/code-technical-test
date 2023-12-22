package org.technical.test.api;

import org.technical.test.model.dao.CustomerDao;
import org.technical.test.model.entity.Customer;
import org.technical.test.model.service.LoginService;
import org.technical.test.model.service.RegisterService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/customer")
public class CustomerResource {

    @Inject
    CustomerDao customerDao;

    @Inject
    private RegisterService registerService;

    @Inject
    private LoginService loginService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/register")
    @Transactional
    public Response registerCustomer(Customer customer) throws Exception{
        
        return Response.ok(registerService.registerCustomer(customer)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    @Transactional
    public Response loginCustomer(Customer customer) throws Exception{
        
        return Response.ok(loginService.loginCustomer(customer)).build();
    }

    @GET
    @Path("findByName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers(@PathParam("name") String name){
        Customer customers = customerDao.findByName(name);
        return Response.ok(customers).build();
    }
    
}
