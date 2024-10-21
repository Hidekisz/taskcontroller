package br.com.vini.taskcontroller.controller;

import br.com.vini.taskcontroller.dto.request.TaskCreateRequest;
import br.com.vini.taskcontroller.dto.request.TaskUpdateDtoRequest;
import br.com.vini.taskcontroller.dto.response.TaskCreateResponse;
import br.com.vini.taskcontroller.dto.response.TaskUpdateDtoResponse;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @GetMapping()
    public String getAllTasks(){
        return "oi";
    }

    @GetMapping("/{id}")
    public String getTaskById(@PathVariable Integer taskId){
        return "oi";
    }

    @PostMapping("/createTask")
    public TaskCreateResponse createTask(@RequestBody TaskCreateRequest taskDto){
        TaskCreateResponse taskUpdateDtoResponse;
        return null;
    }

    @PutMapping("/updateTask/{id}")
    public TaskUpdateDtoResponse updateTaskById(@RequestBody TaskUpdateDtoRequest taskUpdateDtoRequest, @PathVariable Integer id){
        return null;
    }

    @DeleteMapping("/deleteTask/{id}")
    public String deleteTaskById(@PathVariable Integer taskId){
        return "oi";
    }

    @PostMapping("/assignTask/{userId}")
    public String assignTaskByUserId(@PathVariable Integer userId){
        return "oi";
    }
}
