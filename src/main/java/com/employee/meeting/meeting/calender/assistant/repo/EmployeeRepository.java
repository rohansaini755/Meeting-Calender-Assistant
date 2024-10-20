package com.employee.meeting.meeting.calender.assistant.repo;


import com.employee.meeting.meeting.calender.assistant.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

