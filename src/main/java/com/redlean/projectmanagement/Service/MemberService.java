package com.redlean.projectmanagement.Service;

import com.redlean.projectmanagement.model.Member;

import java.util.List;

public interface MemberService {
    public Member createMember(Member member);
    public List<Member> getMembers();
    public Member updateMember(Long memberId, Member member);
    public void deleteMember(Long memberId);
    public List<Member> getMembersByProjectId(Long projectId);
}
