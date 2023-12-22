package org.technical.test.api;

import org.technical.test.model.dao.TaskDao;
import org.technical.test.model.payload.request.AddTaskRequest;
import org.technical.test.model.payload.request.UpdateTaskRequest;
import org.technical.test.model.service.CsrfTokenManagerService;
import org.technical.test.model.service.TaskService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;

@Path("/task")
public class TaskResource {

    @Inject 
    TaskService taskService;

    @Inject 
    CsrfTokenManagerService tokenManagerService;

    @Inject
    TaskDao taskDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTask/{id}/{customerId}")
    @Transactional
    public Response getTask(@PathParam("id") Integer id, @PathParam("customerId") Integer customerId, @Context HttpHeaders headers){
        
        return Response.ok(taskService.getTask(taskDao.findByIdAndCustomer(id, customerId),headers, customerId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getListTask/{customerId}")
    @Transactional
    public Response getListTask(@PathParam("customerId") Integer customerId, @Context HttpHeaders headers){

        return Response.ok(taskService.getListTask(headers, customerId, taskDao.findListByCustomer(customerId))).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getListStateTask/{customerId}/{state}")
    @Transactional
    public Response getListTask(@PathParam("customerId") Integer customerId, @PathParam("state") String state, @Context HttpHeaders headers){

        return Response.ok(taskService.getListTask(headers, customerId, taskDao.findListByCustomerAndState(customerId, state))).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addTask")
    @Transactional
    public Response addTask(AddTaskRequest taskRequest, @Context HttpHeaders headers){

        return Response.ok(taskService.addTask(taskRequest, headers)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("updateTask")
    @Transactional
    public Response updateTask(UpdateTaskRequest taskRequest, @Context HttpHeaders headers){

        return Response.ok(taskService.updateTask(taskRequest, headers)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deleteTask/{id}/{customerId}")
    @Transactional
    public Response deleteTask(@PathParam("id") Integer id, @PathParam("customerId") Integer customerId, @Context HttpHeaders headers){

        return Response.ok(taskService.deleteTask(id, headers, customerId)).build();
    }
    
}
