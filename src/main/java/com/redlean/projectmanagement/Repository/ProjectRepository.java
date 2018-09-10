package com.redlean.projectmanagement.Repository;

import com.redlean.projectmanagement.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT p from Project p where p.id = :projectId")
    public Project getProjectById(@Param("projectId") Long projectId);
}
