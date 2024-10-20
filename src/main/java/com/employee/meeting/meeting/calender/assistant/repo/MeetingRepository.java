package com.employee.meeting.meeting.calender.assistant.repo;


import com.employee.meeting.meeting.calender.assistant.Entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}

