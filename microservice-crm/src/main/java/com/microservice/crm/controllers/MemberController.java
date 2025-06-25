package com.microservice.crm.controllers;

import com.microservice.crm.dto.CreateMemberDTO;
import com.microservice.crm.dto.MemberDTO;
import com.microservice.crm.dto.UpdateMemberDTO;
import com.microservice.crm.services.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crm/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        List<MemberDTO> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/role/{role}")
    public ResponseEntity<List<MemberDTO>> findByRoleActive(@PathVariable String role) {
        List<MemberDTO> members = memberService.findMembersByRoleAndActiveStatus(role);
        return ResponseEntity.ok(members);
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<MemberDTO>> getActiveMembers() {
        List<MemberDTO> members = memberService.findActiveMembers();
        return ResponseEntity.ok(members);
    }



    @GetMapping("/inactive")
    public ResponseEntity<List<MemberDTO>> getInactiveMembers() {
        List<MemberDTO> members = memberService.findInactiveMembers();
        return ResponseEntity.ok(members);
    }
    
    @PutMapping("/{id}/set_access")
    public ResponseEntity<MemberDTO> setActive(@PathVariable Long id) {
        return memberService.updateStatus(id, 1)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/remove_access")
    public ResponseEntity<MemberDTO> setInactive(@PathVariable Long id) {
        return memberService.updateStatus(id, 0)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@RequestBody CreateMemberDTO createDTO) {
        MemberDTO created = memberService.createMember(createDTO);
        return ResponseEntity.ok(created);
    }

    // Actualizar miembro (admin)
    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(@PathVariable Long id, @RequestBody UpdateMemberDTO updateDTO) {
        return memberService.updateMember(id, updateDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Borrar miembro (solo admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
