package com.myApp.services;

import com.myApp.exception.TaskNotFoundException;
import com.myApp.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServices {

    private final List<Task> tasks = new ArrayList<>();

    public TaskServices() {
        tasks.add(new Task(
                1L,
                "Learn Spring Boot",
                "Learn REST APIs",
                false
        ));

        tasks.add(new Task(
                2L,
                "Build Task Manager",
                "Create CRUD APIs",
                false
        ));
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTaskById(Long id) {

        for (Task task : tasks) {

            if (id!=null && id.equals(task.getId())) {
                return task;
            }
        }

        throw new TaskNotFoundException(id);
    }
    public Task updateTask(Long id, Task updatedTask) {

        for (Task task : tasks) {

            if (id != null && id.equals(task.getId())) {

                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                task.setCompleted(updatedTask.isCompleted());

                return task;
            }
        }

        return null;
    }

    public boolean deleteTask(Long id){
        for(Task task:tasks){
            if(id!=null && id.equals(task.getId())){
                tasks.remove(task);
                return true;
            }
        }
        return false;
    }
    public Task createTask(Task task) {
        task.setId((long) (tasks.size() + 1));
        tasks.add(task);

        return task;
    }
}