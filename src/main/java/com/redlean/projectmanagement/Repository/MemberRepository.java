package com.redlean.projectmanagement.Repository;

import com.redlean.projectmanagement.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m from Member m where m.team.id = :teamId ")
    public List<Member> getMembersByTeamId(@Param("teamId") Long teamId);
}
