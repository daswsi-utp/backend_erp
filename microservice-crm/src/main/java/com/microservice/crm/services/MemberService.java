package com.microservice.crm.services;

import com.microservice.crm.dto.CreateMemberDTO;
import com.microservice.crm.dto.EmployeeDTO;
import com.microservice.crm.dto.MemberDTO;
import com.microservice.crm.dto.UpdateMemberDTO;
import com.microservice.crm.entities.Member;
import com.microservice.crm.entities.Team;
import com.microservice.crm.feign.EmployeeFeignClient;
import com.microservice.crm.repositories.MemberRepository;
import com.microservice.crm.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final EmployeeFeignClient employeeFeignClient;
    private final TeamRepository teamRepository;

    public MemberService(MemberRepository memberRepository,
                         EmployeeFeignClient employeeFeignClient,
                         TeamRepository teamRepository) {
        this.memberRepository = memberRepository;
        this.employeeFeignClient = employeeFeignClient;
        this.teamRepository = teamRepository;
    }

    private MemberDTO mapToDTO(Member member) {
        EmployeeDTO employeeInfo;
        try {
            employeeInfo = employeeFeignClient.getEmployeeById(member.getEmployeeId());
        } catch (Exception e) {
            employeeInfo = new EmployeeDTO();
            employeeInfo.setFirstName("Desconocido");
            employeeInfo.setLastName("");
        }

        String fullName = employeeInfo.getFirstName() + " " + employeeInfo.getLastName();

        return new MemberDTO(
                member.getId(),
                member.getEmployeeId(),
                fullName.trim(),
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
        member.setStatus(createDTO.getStatus());

        Team defaultTeam = teamRepository.findByName("Sin Asignar")
                .orElseThrow(() -> new RuntimeException("Equipo 'Sin Asignar' no encontrado"));
        member.setTeam(defaultTeam);

        Member saved = memberRepository.save(member);
        return mapToDTO(saved);
    }

    public Optional<MemberDTO> updateMember(Long id, UpdateMemberDTO updateDTO) {
        Optional<Member> optMember = memberRepository.findById(id);
        if (optMember.isPresent()) {
            Member member = optMember.get();
            member.setFullName(updateDTO.getFullName());
            member.setCrmRole(updateDTO.getCrmRole());
            member.setStatus(updateDTO.getStatus());

            // Si quieres también actualizar el team, aquí lo haces (opcional)
            // Ejemplo:
            // if (updateDTO.getTeamId() != null) {
            //     Team team = teamRepository.findById(updateDTO.getTeamId())
            //         .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
            //     member.setTeam(team);
            // }

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
