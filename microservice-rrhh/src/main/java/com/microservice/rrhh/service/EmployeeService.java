package com.microservice.rrhh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.rrhh.client.MemberClient;
import com.microservice.rrhh.dto.MemberDTO;
import com.microservice.rrhh.model.Department;
import com.microservice.rrhh.model.Employee;
import com.microservice.rrhh.model.EmployeePosition;
import com.microservice.rrhh.model.EmployeeState;
import com.microservice.rrhh.model.Role;
import com.microservice.rrhh.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MemberClient memberClient;
	
	public List<Employee> getEmployees(){return employeeRepository.findAll();}
	
	public Optional<Employee> getEmployeeById(Long id) {return employeeRepository.findById(id);}
	
	public List<Employee> getEmployeesByDepartmentId(Long id){return employeeRepository.findAllByDepartment_Id(id);}
	
	public List<Employee> getEmployeesByRoleId(Long id){return employeeRepository.findAllByRole_Id(id);}
	
	public List<Employee> getEmployeeByState(EmployeeState employeeState){return employeeRepository.findByState(employeeState);}
	
	public List<Employee> getEmployeeByPosition(EmployeePosition employeePosition){return employeeRepository.findByPosition(employeePosition);}
	
	public Employee createEmployee(Employee employee){
		System.out.println("ENTRO 000");
		employee.setAccount("DESACTIVADO");
		Employee employeeNew = employeeRepository.save(employee);
		Optional<Department> departEmp = departmentService.getDepartmentById(employeeNew.getDepartment().getId());
		System.out.println("ENTRO 1111");
		if (departEmp.isPresent()) {
			System.out.println("ENTRO 2222");
	        Department dept = departEmp.get();
	        if ("CRM".equalsIgnoreCase(dept.getCode())){
	        	System.out.println("ENTRO 3333");
	        	Optional<Role> roleOp = roleService.getRoleById(employeeNew.getRole().getId());
	    		Role role = roleOp.get();
	        	MemberDTO member = MemberDTO.builder()
	        		    .employeeId(employeeNew.getId())
	        		    .crmRole(role.getName())
	        		    .teamId(1L)
	        		    .status(1)
	        		    .build();
	        	memberClient.createMember(member);
	        }
	    }
		return employeeNew;
	}
	
	public Employee updateEmployee(Employee employee){return employeeRepository.save(employee);}
	
	public void deleteEmployee(Long id){employeeRepository.deleteById(id);}
	
}
