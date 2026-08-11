package com.myApp.Controller;

import com.myApp.model.Task;
import com.myApp.services.TaskServices;
import org.springframework.http.HttpStatus;
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
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/api/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {

        Task task = taskService.getTaskById(id);

        return ResponseEntity.ok(task);
    }

    @PostMapping("/api/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {

        Task createdTask = taskService.createTask(task);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdTask);
    }

    @PutMapping("/api/tasks/{id}")
    public Task updateTask(
            @PathVariable Long id,
            @RequestBody Task task) {

        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/api/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {

        boolean deleted = taskService.deleteTask(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}