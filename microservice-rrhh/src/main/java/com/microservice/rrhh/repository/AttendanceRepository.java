package com.microservice.rrhh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.rrhh.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	List<Attendance> findAllByEmployee_Id(Long id);
}
