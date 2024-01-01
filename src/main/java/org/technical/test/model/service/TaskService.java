package org.technical.test.model.service;

import java.util.List;

import org.technical.test.global.ErrorCode;
import org.technical.test.model.dao.CustomerDao;
import org.technical.test.model.dao.TaskDao;
import org.technical.test.model.entity.Task;
import org.technical.test.model.payload.request.AddTaskRequest;
import org.technical.test.model.payload.request.UpdateTaskRequest;
import org.technical.test.model.payload.response.AddTaskResponse;
import org.technical.test.model.payload.response.DeleteTaskResponse;
import org.technical.test.model.payload.response.GetListTaskResponse;
import org.technical.test.model.payload.response.GetTaskResponse;
import org.technical.test.model.payload.response.UpdateTaskResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.HttpHeaders;

@ApplicationScoped
public class TaskService {

    @Inject
    TaskDao taskDao;

    @Inject 
    CsrfTokenManagerService tokenManagerService;

    @Inject
    CustomerDao customerDao;

    public AddTaskResponse addTask(AddTaskRequest taskRequest, HttpHeaders headers){

        AddTaskResponse taskResponse = new AddTaskResponse();

        if(tokenManagerService.validateToken(headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer ".length()), taskRequest.getCustomer_id())==false){
            taskResponse.setCodeError(ErrorCode.ERROR_106);  
            taskResponse.setDescription(ErrorCode.ERROR_106_DESC);
            taskResponse.setMessage(ErrorCode.ERROR_106_MSG);   
            taskResponse.setError(true);
            return taskResponse;
        }

        Task task = new Task();
        
        Task taskTemp1 = taskDao.findByDescriptionAndCustomer(taskRequest.getDescription(), taskRequest.getCustomer_id());
        if (taskTemp1==null){

            task.setDescription(taskRequest.getDescription());
            task.setState(taskRequest.getState());
            task.setImage_url(taskRequest.getImage_url());
            task.setEnabled(true);
            task.setCustomer(customerDao.findById(taskRequest.getCustomer_id()));
            taskDao.persist(task);
            taskResponse.setTask(task);
            taskResponse.setError(false);

        }else{
            Task taskTemp2 = taskDao.findByDescriptionAndStatusAndCustomer(taskRequest.getDescription(), true, taskRequest.getCustomer_id());
            if(taskTemp2==null){
                taskTemp1.setEnabled(true);
                taskDao.persist(taskTemp1);
                taskResponse.setTask(taskTemp1);
                taskResponse.setError(false);
            }else{
                taskResponse.setCodeError(ErrorCode.ERROR_104);  
                taskResponse.setDescription(ErrorCode.ERROR_104_DESC);
                taskResponse.setMessage(ErrorCode.ERROR_104_MSG);  
                taskResponse.setError(true);
            }
        }
        return taskResponse;
    }

    public GetTaskResponse getTask(Task task, HttpHeaders headers , Integer customerId){
        GetTaskResponse taskResponse = new GetTaskResponse();

        if(tokenManagerService.validateToken(headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer ".length()), customerId)){
            taskResponse.setError(false);
            taskResponse.setTask(task);
        }else{
            taskResponse.setCodeError(ErrorCode.ERROR_106);  
            taskResponse.setDescription(ErrorCode.ERROR_106_DESC);
            taskResponse.setMessage(ErrorCode.ERROR_106_MSG);  
            taskResponse.setError(true);
        }
        
        return taskResponse;
    }

    public GetListTaskResponse getListTask(HttpHeaders headers, Integer customerId, List<Task> task){
        GetListTaskResponse taskResponse = new GetListTaskResponse();

        if(tokenManagerService.validateToken(headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer ".length()), customerId)==false){
            taskResponse.setCodeError(ErrorCode.ERROR_106);  
            taskResponse.setDescription(ErrorCode.ERROR_106_DESC);
            taskResponse.setMessage(ErrorCode.ERROR_106_MSG);  
            taskResponse.setError(true);
            return taskResponse;
        }else{
            taskResponse.setError(false);
            taskResponse.setTask(task);
        }
        
        return taskResponse;
    }

    public UpdateTaskResponse updateTask(UpdateTaskRequest taskRequest, HttpHeaders headers){
        UpdateTaskResponse taskResponse = new UpdateTaskResponse();

        if(tokenManagerService.validateToken(headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer ".length()), taskRequest.getCustomer_id())==false){
            taskResponse.setCodeError(ErrorCode.ERROR_106);  
            taskResponse.setDescription(ErrorCode.ERROR_106_DESC);
            taskResponse.setMessage(ErrorCode.ERROR_106_MSG);  
            taskResponse.setError(true);
            return taskResponse;
        }
        
        Task taskTemp1 = taskDao.findByDescriptionAndStatusAndCustomer(taskRequest.getDescription(), true,taskRequest.getCustomer_id());
        if (taskTemp1!=null){
            taskTemp1.setDescription(taskRequest.getDescription());
            taskTemp1.setState(taskRequest.getState());
            taskTemp1.setImage_url(taskRequest.getImage_url());
            taskTemp1.setEnabled(true);
            taskTemp1.setCustomer(customerDao.findById(taskRequest.getCustomer_id()));
            taskDao.persist(taskTemp1);
            taskResponse.setTask(taskTemp1);
            taskResponse.setError(false);

        }else{
            taskResponse.setCodeError(ErrorCode.ERROR_105);  
            taskResponse.setDescription(ErrorCode.ERROR_105_DESC);
            taskResponse.setMessage(ErrorCode.ERROR_105_MSG);  
            taskResponse.setError(true);
        }
        
        return taskResponse;
    }

    public DeleteTaskResponse deleteTask(Integer id, HttpHeaders headers, Integer customerId){
        DeleteTaskResponse taskResponse = new DeleteTaskResponse();

        if(tokenManagerService.validateToken(headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer ".length()), customerId)==false){
            taskResponse.setCodeError(ErrorCode.ERROR_106);  
            taskResponse.setDescription(ErrorCode.ERROR_106_DESC);
            taskResponse.setMessage(ErrorCode.ERROR_106_MSG);  
            taskResponse.setError(true);
            return taskResponse;
        }
        
        Task taskTemp1 = taskDao.findByIdAndCustomer(id, customerId);
        if (taskTemp1!=null){
            taskTemp1.setEnabled(false);
            taskDao.persist(taskTemp1);
            taskResponse.setTask(taskTemp1);
            taskResponse.setError(false);

        }else{
            taskResponse.setCodeError(ErrorCode.ERROR_105);  
            taskResponse.setDescription(ErrorCode.ERROR_105_DESC);
            taskResponse.setMessage(ErrorCode.ERROR_105_MSG);  
            taskResponse.setError(true);
        }
        
        return taskResponse;
    }
    
}
