package com.redlean.projectmanagement.Service.Impl;

import com.redlean.projectmanagement.Exception.ResourceNotFoundException;
import com.redlean.projectmanagement.Repository.MemberRepository;
import com.redlean.projectmanagement.Repository.ProjectRepository;
import com.redlean.projectmanagement.Repository.TeamRepository;
import com.redlean.projectmanagement.Service.MemberService;
import com.redlean.projectmanagement.Service.ProjectService;
import com.redlean.projectmanagement.model.Member;
import com.redlean.projectmanagement.model.Project;
import com.redlean.projectmanagement.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;
    private TeamRepository teamRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, TeamRepository teamRepository, ProjectRepository projectRepository) {
        this.memberRepository = memberRepository;
        this.teamRepository = teamRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Member createMember(Member member) {
        Member newMember = new Member();
        newMember.setFirstName(member.getFirstName());
        newMember.setLastName(member.getLastName());
        newMember.setRole(member.getRole());
        if(member.getTeam().getId() != null){
            Team team = teamRepository.findTeamById(member.getTeam().getId());
            if(team != null){
                newMember.setTeam(team);
            } else {
                newMember.setTeam(member.getTeam());
            }
        }else{
            newMember.setTeam(member.getTeam());
        }
        return memberRepository.save(newMember);
        //return memberRepository.save(member);
    }

    @Override
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member updateMember(Long memberId, Member member) {
        return memberRepository.findById(memberId).map(newMember -> {
            newMember.setFirstName(member.getFirstName());
            newMember.setLastName(member.getLastName());
            newMember.setRole(member.getRole());
            newMember.setTeam(member.getTeam());
            return memberRepository.save(newMember);
        }).orElseThrow(() -> new ResourceNotFoundException("The Member Id " + memberId + " is not found"));
    }

    @Override
    public void deleteMember(Long memberId) {
        if(!memberRepository.existsById(memberId)){
            throw new ResourceNotFoundException("The Member Id " + memberId + " is not found");
        }
        memberRepository.deleteById(memberId);
    }

    @Override
    public List<Member> getMembersByProjectId(Long projectId) {
        if(!projectRepository.existsById(projectId)){
            throw new ResourceNotFoundException("The Project Id " + projectId + " is not found");
        }
        return memberRepository.getMembersByTeamId(projectRepository.getProjectById(projectId).getTeam().getId());
    }
}
