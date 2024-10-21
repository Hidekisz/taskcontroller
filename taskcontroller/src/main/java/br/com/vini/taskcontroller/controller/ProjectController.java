package br.com.vini.taskcontroller.controller;

import br.com.vini.taskcontroller.dto.MembersDto;
import br.com.vini.taskcontroller.dto.request.ProjectCreateRequest;
import br.com.vini.taskcontroller.dto.request.ProjectUpdateRequest;
import br.com.vini.taskcontroller.dto.request.TaskCreateRequest;
import br.com.vini.taskcontroller.dto.request.TaskUpdateDtoRequest;
import br.com.vini.taskcontroller.dto.response.ProjectCreateResponse;
import br.com.vini.taskcontroller.dto.response.ProjectUpdateResponse;
import br.com.vini.taskcontroller.dto.response.TaskCreateResponse;
import br.com.vini.taskcontroller.dto.response.TaskUpdateDtoResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @GetMapping()
    public String getAllProjects(){
        return "oi";
    }

    @GetMapping("/{id}")
    public String getProjectByProjectId(@PathVariable Integer projectId){
        return "oi";
    }

    @PostMapping("/createProject")
    public ProjectCreateResponse createProject(@RequestBody ProjectCreateRequest projectCreateRequest){
        ProjectCreateResponse taskUpdateDtoResponse = null;
        return taskUpdateDtoResponse;
    }

    @PutMapping("/updateProject/{id}")
    public ProjectUpdateResponse updateProjectById(@RequestBody ProjectUpdateRequest projectUpdateRequest, @PathVariable Integer projectId){
        ProjectUpdateResponse projectUpdateResponse = null;
        return projectUpdateResponse;
    }

    @DeleteMapping("/deleteProject/{id}")
    public String deleteProjectById(@PathVariable Integer projectId){
        return "oi";
    }

    @PostMapping("/{projectId}/members")
    public String assignProjectByMembers(@PathVariable Integer projectId, @RequestBody MembersDto members){
        return "oi";
    }

    @DeleteMapping("/{projectId}/members/{userId}")
    public String assignProjectByMembers(@PathVariable Integer projectId, @PathVariable Integer userId){
        return "oi";
    }

}
