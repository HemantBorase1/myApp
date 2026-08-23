package com.myApp.Controller;

import com.myApp.dto.TaskRequest;
import com.myApp.dto.TaskResponse;
import com.myApp.model.Task;
import com.myApp.services.TaskServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private final TaskServices taskService;

    public TaskController(TaskServices taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/api/tasks")
    public List<TaskResponse> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/api/tasks/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/api/tasks")
    public TaskResponse createTask(
            @Valid @RequestBody TaskRequest taskRequest) {

        return taskService.createTask(taskRequest);
    }
    @PutMapping("/api/tasks/{id}")
    public TaskResponse updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest taskRequest) {

        return taskService.updateTask(id, taskRequest);
    }

    @DeleteMapping("/api/tasks/{id}")
    public String deleteTask(@PathVariable Long id) {

        boolean deleted = taskService.deleteTask(id);

        if (!deleted) {
            return "Task not found";
        }

        return "Task deleted successfully";
    }
}