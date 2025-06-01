package com.microservice.crm.services;

import com.microservice.crm.dto.CreateMemberDTO;
import com.microservice.crm.dto.EmployeeDTO;
import com.microservice.crm.dto.MemberDTO;
import com.microservice.crm.dto.UpdateMemberDTO;
import com.microservice.crm.entities.Member;
import com.microservice.crm.entities.Status;
import com.microservice.crm.entities.Team;
import com.microservice.crm.feign.EmployeeFeignClient;
import com.microservice.crm.repositories.MemberRepository;
import com.microservice.crm.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        // Obtener el nombre completo del empleado
        EmployeeDTO employeeInfo;
        try {
            employeeInfo = employeeFeignClient.getEmployeeById(member.getEmployeeId());
        } catch (Exception e) {
            employeeInfo = new EmployeeDTO();
            employeeInfo.setFirstName("Desconocido");
            employeeInfo.setLastName("");
        }

        // Obtener la información del equipo (teamName)
        String teamName = member.getTeam() != null ? member.getTeam().getName() : "Sin Asignar";

        // Convertir la fecha de creación (createdAt) en formato legible
        String createdAt = member.getCreatedAt() != null
                ? member.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                : "No disponible";

        return MemberDTO.builder()
    	    .id(member.getId())
    	    .employeeId(member.getEmployeeId())
    	    .fullName(employeeInfo.getFirstName() + " " + employeeInfo.getLastName())
    	    .crmRole(member.getCrmRole())
    	    .teamId(member.getTeam() != null ? member.getTeam().getId() : null)
    	    .status(member.getStatus().getCode() )
    	    .phone(employeeInfo.getPhone())
    	    .address(employeeInfo.getAddress())
    	    .teamName(teamName)
    	    .createdAt(createdAt)
    	    .build();
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
        member.setCrmRole(createDTO.getCrmRole());
        member.setStatus(Status.fromCode(createDTO.getStatus()));
        member.setCreatedAt(LocalDateTime.now());  // Asignar la fecha de creación

        EmployeeDTO employeeInfo;
        try {
            employeeInfo = employeeFeignClient.getEmployeeById(createDTO.getEmployeeId());
        } catch (Exception e) {
            employeeInfo = new EmployeeDTO();
            employeeInfo.setFirstName("Desconocido");
            employeeInfo.setLastName("");
        }

        String fullName = employeeInfo.getFirstName() + " " + employeeInfo.getLastName();
        member.setFullName(fullName.trim());

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
            member.setStatus(Status.fromCode(updateDTO.getStatus()));

            if (updateDTO.getTeamId() != null) {
                Team team = teamRepository.findById(updateDTO.getTeamId())
                        .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
                member.setTeam(team);
            }

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
