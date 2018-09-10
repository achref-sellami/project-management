package com.redlean.projectmanagement.Controller;

import com.redlean.projectmanagement.Exception.ResourceNotFoundException;
import com.redlean.projectmanagement.Service.MemberService;
import com.redlean.projectmanagement.model.Member;
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
@RequestMapping("/members")
public class MemberController {

    private static final Logger LOG = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "getAllMembers")
    public ResponseEntity<List<Member>> getMembers(){
        try{
            return new ResponseEntity<>(memberService.getMembers(), HttpStatus.OK);
        }catch (Exception e){
            LOG.error("An exception occurred while getting all members", e);
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Member> addMember(@Valid @RequestBody Member member){
        try {
            return new ResponseEntity<>(memberService.createMember(member), HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            LOG.error("An exception occurred while adding a member", e);
            return new ResponseEntity<>(new Member(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/edit/{memberId}")
    public ResponseEntity<?> editMember(@PathVariable (value = "memberId") Long memberId, @Valid @RequestBody Member member){
        try {
            return new ResponseEntity<>(memberService.updateMember(memberId, member), HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            LOG.error("An exception occurred while updatting a member", e);
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{memberId}")
    public ResponseEntity<String> deleteMember(@PathVariable (value = "memberId") Long memberId){
        try{
            memberService.deleteMember(memberId);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            LOG.error("An exception occurred while deleting a member", e);
            return new ResponseEntity<>("The Member Id " + memberId + " is not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "getMembers/project/{projectId}")
    public ResponseEntity<List<Member>> getMembersByProjectId(@PathVariable (value = "projectId") Long projectId){
        try {
            return new ResponseEntity<>(memberService.getMembersByProjectId(projectId), HttpStatus.OK);
        }catch (ResourceNotFoundException r) {
            LOG.error("An exception occurred while getting members By projectId " + projectId, r);
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
