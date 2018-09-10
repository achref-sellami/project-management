package com.redlean.projectmanagement.Controller;

import com.redlean.projectmanagement.Exception.ResourceNotFoundException;
import com.redlean.projectmanagement.Service.ProjectService;
import com.redlean.projectmanagement.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "getAll")
    public ResponseEntity<List<Project>> getProjects(){
        try {
            return new ResponseEntity<>(projectService.getProjects(), HttpStatus.OK);
        } catch (Exception e){
            LOGGER.error("An exception occurred while getting all projects",e.getMessage());
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Project> add(@Valid @RequestBody Project project){
        try{
            return new ResponseEntity<>(projectService.createProject(project), HttpStatus.OK);
        } catch (ResourceNotFoundException e){
            LOGGER.error("An exception occurred while adding a project",e);
            return new ResponseEntity<>(new Project(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/edit/{projectId}")
    public ResponseEntity<?> editProject(@PathVariable (value = "projectId") Long projectId, @Valid @RequestBody Project project){
        try {
            return new ResponseEntity<>(projectService.updateProject(projectId, project), HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            LOGGER.error("An exception occurred while updating a project",e);
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable (value = "projectId") Long projectId){
        try{
            projectService.deleteProject(projectId);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            LOGGER.error("An exception occurred while deleting a project", e);
            return new ResponseEntity<>("The Project Id "+ projectId + " not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
