package com.redlean.projectmanagement.Service.Impl;

import com.redlean.projectmanagement.Exception.ResourceNotFoundException;
import com.redlean.projectmanagement.Repository.ProjectRepository;
import com.redlean.projectmanagement.Repository.TeamRepository;
import com.redlean.projectmanagement.Service.ProjectService;
import com.redlean.projectmanagement.model.Project;
import com.redlean.projectmanagement.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    private ProjectRepository projectRepository;
    private TeamRepository teamRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, TeamRepository teamRepository) {
        this.projectRepository = projectRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public Project createProject(Project project) {
        Project newProject = new Project();
        newProject.setTitle(project.getTitle());
        newProject.setDescription(project.getDescription());
        newProject.setProjectLeaderName(project.getProjectLeaderName());
        newProject.setSprintNumber(project.getSprintNumber());
        newProject.setDelivery(project.getDelivery());
        if(project.getTeam().getId() != null){
            Team team = teamRepository.findTeamById(project.getTeam().getId());
            if(team != null){
                newProject.setTeam(team);
            } else {
                newProject.setTeam(project.getTeam());
            }
        }else{
            newProject.setTeam(project.getTeam());
        }
        return projectRepository.save(newProject);
        //return projectRepository.save(project);
    }

    @Override
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project updateProject(Long projectId, Project project) {
        return projectRepository.findById(projectId).map(newProject -> {
            newProject.setTitle(project.getTitle());
            newProject.setDelivery(project.getDelivery());
            newProject.setDescription(project.getDescription());
            newProject.setProjectLeaderName(project.getProjectLeaderName());
            newProject.setTeam(project.getTeam());
            return projectRepository.save(newProject);
        }).orElseThrow(() -> new ResourceNotFoundException("The Project Id "+ projectId + " is not found"));
    }

    @Override
    public void deleteProject(Long projectId) {
        if(!projectRepository.existsById(projectId)){
            throw new ResourceNotFoundException("The Project Id "+ projectId + " not found");
        }
        projectRepository.deleteById(projectId);
    }
}
