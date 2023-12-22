package org.technical.test.model.service;

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

@ApplicationScoped
public class TaskService {

    @Inject
    TaskDao taskDao;

    @Inject
    CustomerDao customerDao;

    public AddTaskResponse addTask(AddTaskRequest taskRequest){

        AddTaskResponse taskResponse = new AddTaskResponse();
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
                taskResponse.setCodeError(405);  
                taskResponse.setDescription("User entered an exist and active task");
                taskResponse.setMessage("ERROR: Task Already exist and is active");  
                taskResponse.setError(true);
            }
        }
        return taskResponse;
    }

    public GetTaskResponse getTask(String id){
        GetTaskResponse taskResponse = new GetTaskResponse();
        
        return taskResponse;
    }

    public GetListTaskResponse getListTask(){
        GetListTaskResponse taskResponse = new GetListTaskResponse();
        
        return taskResponse;
    }

    public UpdateTaskResponse updateTask(UpdateTaskRequest taskRequest){
        UpdateTaskResponse taskResponse = new UpdateTaskResponse();
        Task task = new Task();
        
        Task taskTemp1 = taskDao.findByDescriptionAndCustomer(taskRequest.getDescription(), taskRequest.getCustomer_id());
        if (taskTemp1!=null){
            task.setDescription(taskRequest.getDescription());
            task.setState(taskRequest.getState());
            task.setImage_url(taskRequest.getImage_url());
            task.setEnabled(true);
            task.setCustomer(customerDao.findById(taskRequest.getCustomer_id()));
            taskDao.persist(task);
            taskResponse.setTask(task);
            taskResponse.setError(false);

        }else{
            taskResponse.setCodeError(406);  
            taskResponse.setDescription("User entered info for an unknow task");
            taskResponse.setMessage("ERROR: Task not exist");  
            taskResponse.setError(true);
        }
        
        return taskResponse;
    }

    public DeleteTaskResponse deleteTask(String id){
        DeleteTaskResponse taskResponse = new DeleteTaskResponse();
        
        return taskResponse;
    }
    
}
