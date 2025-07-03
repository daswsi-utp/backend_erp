package com.microservice.crm.controllers;

import com.microservice.crm.dto.LeadAssignmentDTO;
import com.microservice.crm.dto.ProductLeadsDTO;
import com.microservice.crm.services.ProductMemberService;
import com.microservice.crm.services.LeadAssignmentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crm/members")
public class ProductMemberController {

    private final ProductMemberService productMemberService;
    private final LeadAssignmentService leadAssignmentService;

    public ProductMemberController(ProductMemberService productMemberService, LeadAssignmentService leadAssignmentService) {
        this.productMemberService = productMemberService;
        this.leadAssignmentService = leadAssignmentService;
    }

    @GetMapping("/{memberId}/leads")
    public List<ProductLeadsDTO> getLeadsForMember(@PathVariable Long memberId) {
        return productMemberService.getLeadsForMember(memberId);
    }

    @PutMapping("/{memberId}/assign-leads")
    public ResponseEntity<Void> assignLeads(@PathVariable Long memberId, @RequestBody List<LeadAssignmentDTO> leadAssignments) {
        try {
            leadAssignmentService.assignLeads(memberId, leadAssignments);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
