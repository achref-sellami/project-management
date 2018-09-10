package com.redlean.projectmanagement.Repository;

import com.redlean.projectmanagement.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT t from Team t where id = :teamId")
    Team findTeamById(@Param("teamId") Long teamId);
}
