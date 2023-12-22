package org.technical.test.api;

import org.technical.test.model.entity.Task;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/task")
public class TaskResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getTask")
    @Transactional
    public Response getTask(Task task){
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getListTask")
    @Transactional
    public Response getListTask(Task task){
        return Response.ok().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addTask")
    @Transactional
    public Response addTask(Task task){
        return Response.ok().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("updateTask")
    @Transactional
    public Response updateTask(Task task){
        return Response.ok().build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deleteTask/{id}")
    @Transactional
    public Response deleteTask(Task task){
        return Response.ok().build();
    }
    
}
