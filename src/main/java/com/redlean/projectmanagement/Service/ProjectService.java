package com.redlean.projectmanagement.Service;

import com.redlean.projectmanagement.model.Project;

import java.util.List;

public interface ProjectService {
    public Project createProject(Project project);
    public List<Project> getProjects();
    public Project updateProject(Long projectId, Project project);
    public void deleteProject(Long projectId);
}
