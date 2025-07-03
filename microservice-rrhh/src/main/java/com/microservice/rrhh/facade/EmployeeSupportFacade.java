package com.microservice.rrhh.facade;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microservice.rrhh.client.MemberClient;
import com.microservice.rrhh.dto.MemberDTO;
import com.microservice.rrhh.model.Department;
import com.microservice.rrhh.model.Employee;
import com.microservice.rrhh.model.Role;
import com.microservice.rrhh.service.DepartmentService;
import com.microservice.rrhh.service.RoleService;

@Component
public class EmployeeSupportFacade {

	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MemberClient memberClient;
	
	public void handleCrmMemberCreation(Employee employee) {
		Optional<Department> departEmp = departmentService.getDepartmentById(employee.getDepartment().getId());
		if (departEmp.isPresent()) {
	        Department dept = departEmp.get();
	        if ("CRM".equalsIgnoreCase(dept.getCode())){
	        	System.out.println("ENTRO 3333");
	        	Optional<Role> roleOp = roleService.getRoleById(employee.getRole().getId());
	    		Role role = roleOp.get();
	        	MemberDTO member = MemberDTO.builder()
	        		    .employeeId(employee.getId())
	        		    .crmRole(role.getName())
	        		    .teamId(1L)
	        		    .status(1)
	        		    .build();
	        	memberClient.createMember(member);
	        }
	    }
	}
	
}
