package com.employee.meeting.meeting.calender.assistant.Services;




import com.employee.meeting.meeting.calender.assistant.Entity.Employee;
import com.employee.meeting.meeting.calender.assistant.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}




@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //to get the employee
    public Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }

    //to register the employee
    public String registerEmployee(Employee employee){
        employeeRepository.save(employee);
        return "employee registered successfully";
    }
}
