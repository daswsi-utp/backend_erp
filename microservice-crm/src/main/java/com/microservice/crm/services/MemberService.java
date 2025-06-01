package com.microservice.crm.services;

import com.microservice.crm.dto.CreateMemberDTO;
import com.microservice.crm.dto.MemberDTO;
import com.microservice.crm.dto.UpdateMemberDTO;
import com.microservice.crm.entities.Member;
import com.microservice.crm.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private MemberDTO mapToDTO(Member member) {
        return new MemberDTO(
                member.getId(),
                member.getEmployeeId(),
                member.getFullName(),
                member.getCrmRole(),
                member.getTeam() != null ? member.getTeam().getId() : null,
                member.getStatus()
        );
    }

    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<MemberDTO> getMemberById(Long id) {
        return memberRepository.findById(id)
                .map(this::mapToDTO);
    }

    public MemberDTO createMember(CreateMemberDTO createDTO) {
        Member member = new Member();
        member.setEmployeeId(createDTO.getEmployeeId());
        member.setFullName(createDTO.getFullName());
        member.setCrmRole(createDTO.getCrmRole());
        // TODO: Fetch Team entity by createDTO.getTeamId() and set here
        member.setStatus(createDTO.getStatus());

        Member saved = memberRepository.save(member);
        return mapToDTO(saved);
    }

    public Optional<MemberDTO> updateMember(Long id, UpdateMemberDTO updateDTO) {
        Optional<Member> optMember = memberRepository.findById(id);
        if (optMember.isPresent()) {
            Member member = optMember.get();
            member.setFullName(updateDTO.getFullName());
            member.setCrmRole(updateDTO.getCrmRole());
            // TODO: Fetch Team entity by updateDTO.getTeamId() and set here
            member.setStatus(updateDTO.getStatus());
            Member updated = memberRepository.save(member);
            return Optional.of(mapToDTO(updated));
        }
        return Optional.empty();
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public List<MemberDTO> findMembersByRole(String crmRole) {
        return memberRepository.findByCrmRole(crmRole)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<MemberDTO> findMembersByTeamId(Long teamId) {
        return memberRepository.findByTeamId(teamId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
