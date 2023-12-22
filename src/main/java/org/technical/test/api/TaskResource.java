package org.technical.test.api;

import org.technical.test.model.dao.TaskDao;
import org.technical.test.model.entity.Task;
import org.technical.test.model.payload.request.AddTaskRequest;
import org.technical.test.model.payload.request.UpdateTaskRequest;
import org.technical.test.model.service.TaskService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/task")
public class TaskResource {

    @Inject 
    TaskService taskService;

    @Inject
    TaskDao taskDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTask/{id}/{customerId}")
    @Transactional
    public Response getTask(@PathParam("id") Integer id, @PathParam("customerId") Integer customerId){

        return Response.ok(taskDao.findByIdAndCustomer(id, customerId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getListTask/{customerId}")
    @Transactional
    public Response getListTask(@PathParam("customerId") Integer customerId){

        return Response.ok(taskDao.findListByCustomer(customerId)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addTask")
    @Transactional
    public Response addTask(AddTaskRequest taskRequest){

        return Response.ok(taskService.addTask(taskRequest)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("updateTask")
    @Transactional
    public Response updateTask(UpdateTaskRequest taskRequest){

        return Response.ok(taskService.updateTask(taskRequest)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deleteTask/{id}/{customerId}")
    @Transactional
    public Response deleteTask(@PathParam("id") Integer id, @PathParam("customerId") Integer customerId){

        return Response.ok(taskService.deleteTask(id, customerId)).build();
    }
    
}
