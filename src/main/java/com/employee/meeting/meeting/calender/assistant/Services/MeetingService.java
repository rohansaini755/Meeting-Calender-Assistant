package com.employee.meeting.meeting.calender.assistant.Services;




import com.employee.meeting.meeting.calender.assistant.Entity.Employee;
import com.employee.meeting.meeting.calender.assistant.Entity.Meeting;
import com.employee.meeting.meeting.calender.assistant.dto.MeetingRequestData;
import com.employee.meeting.meeting.calender.assistant.repo.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeetingService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MeetingRepository meetingRepository;

    public void bookMeeting(Long employeeId, MeetingRequestData meetingRequest) {
        Employee owner = employeeService.getEmployee(employeeId);

        //converting meeting information into meeting object
        Meeting meeting = new Meeting();
        meeting.setStartTime(meetingRequest.getStartTime());
        meeting.setEndTime(meetingRequest.getEndTime());
        meeting.setOwner(owner);


        //using stream api to fetch participants id of meeting and using employeeService
        //method getEmployee() fetching Employee object and storing it into list.
        List<Employee> participants = meetingRequest.getParticipantIds().stream()
                .map(employeeService::getEmployee)
                .toList();


        meeting.setParticipants(participants);

        //using repository storing data into temporary database.
        meetingRepository.save(meeting);
    }
}
