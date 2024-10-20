package com.employee.meeting.meeting.calender.assistant.controllers;

import com.employee.meeting.meeting.calender.assistant.Entity.Employee;
import com.employee.meeting.meeting.calender.assistant.Services.EmployeeService;
import com.employee.meeting.meeting.calender.assistant.Services.FreeTimeSlotService;
import com.employee.meeting.meeting.calender.assistant.Services.MeetingService;
import com.employee.meeting.meeting.calender.assistant.dto.MeetingRequestData;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class Controller {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private FreeTimeSlotService timeSlotService;

    @GetMapping
    public String health_check(){return "calender assistant health check";}

    @GetMapping("/{id}/path")
    public String parameterFunction(@PathVariable int id){return "parameter " + id + " is also working.";}

    @PostMapping("/register")
    public ResponseEntity<String> registerEmployee(@RequestBody Employee employee){
        String ans = employeeService.registerEmployee(employee);
        return ResponseEntity.ok(ans);
    }

    @PostMapping("/{id}/book-meeting-check")
    public ResponseEntity<String> bookMeetingFunc(@PathVariable long id , @RequestBody MeetingRequestData data){
        meetingService.bookMeeting(id, data);
        return ResponseEntity.ok("this is working. Meeting book successfully.");
    }

    @PostMapping("/{employeeId}/book-meeting")
    public ResponseEntity<String> bookMeeting(@PathVariable Long employeeId, @RequestBody MeetingRequestData meetingRequest) {
        meetingService.bookMeeting(employeeId, meetingRequest);
        return ResponseEntity.ok("Meeting booked successfully!");
    }

    @GetMapping("/{employeeId}/free-slots")
    public ResponseEntity<List<LocalDateTime[]>> getFreeSlots(@PathVariable Long employeeId, @RequestParam long durationMinutes) {
        //Getting employee info from employee id
        Employee employee = employeeService.getEmployee(employeeId);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        Duration duration = Duration.ofMinutes(durationMinutes);
        List<LocalDateTime[]> freeSlots = timeSlotService.findFreeSlots(employee, duration);
        return ResponseEntity.ok(freeSlots);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<LocalDateTime[]>> findConflicts(@PathVariable Long employeeId){
        //Getting employee info from employee id
        Employee employee = employeeService.getEmployee(employeeId);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }


        //function calling to find the list of conflicts meeting start time
        List<LocalDateTime[]> conflistMeetings = timeSlotService.findConflicts(employee);

        return ResponseEntity.ok(conflistMeetings);

    }
}
