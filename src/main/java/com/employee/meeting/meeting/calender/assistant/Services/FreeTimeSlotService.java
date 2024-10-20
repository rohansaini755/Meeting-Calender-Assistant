package com.employee.meeting.meeting.calender.assistant.Services;



import com.employee.meeting.meeting.calender.assistant.Entity.Employee;
import com.employee.meeting.meeting.calender.assistant.Entity.Meeting;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FreeTimeSlotService {

    public List<LocalDateTime[]> findConflicts(Employee employee){

        List<Meeting> meetings = employee.getMeetings();
        List<LocalDateTime[]> conflicts = new ArrayList<>();

        //sort the meeting list according to the start time of th meetings.
        meetings.sort((m1, m2) -> m1.getStartTime().compareTo(m2.getStartTime()));

        for(int i = 0; i < meetings.size() - 1; i++){
            Meeting m1 = meetings.get(i);
            Meeting m2 = meetings.get(i + 1);

            //if gap between end time of a meeting and start time of the
            // next meeting end time is < 0, it means there is a conflicts

            if(m1.getEndTime().compareTo(m2.getStartTime()) > 0){
                //if conflicts occure, store the both meetings start time.
                conflicts.add(new LocalDateTime[]{m1.getStartTime(), m2.getStartTime()});
            }
        }
        return conflicts;
    }

    public List<LocalDateTime[]> findFreeSlots(Employee employee, Duration duration) {
        List<Meeting> meetings = employee.getMeetings();
        List<LocalDateTime[]> freeSlots = new ArrayList<>();

        // Logic to find gaps between meetings and return free slots

        //start time of the meetings
        LocalDateTime startOfDay = LocalDateTime.now().withHour(9).withMinute(0).withSecond(0).withNano(0);
        //end time the office
        LocalDateTime endOfDay = LocalDateTime.now().withHour(18).withMinute(0);


        //sort the meeting according to the start time
        meetings.sort((m1,m2) -> m1.getStartTime().compareTo(m2.getStartTime()));

        for (int i = 0; i < meetings.size() - 1; i++) {
            Meeting current = meetings.get(i);
            Meeting next = meetings.get(i + 1);

            //duration between meeting end time and next meeting start time is >= given time duration
            if (Duration.between(current.getEndTime(), next.getStartTime()).compareTo(duration) >= 0) {
                //add that time slots
                freeSlots.add(new LocalDateTime[]{current.getEndTime(), next.getStartTime()});
            }
        }

        // Add before the first meeting and after the last one

        if (Duration.between(startOfDay, meetings.get(0).getStartTime()).compareTo(duration) >= 0) {
            freeSlots.add(new LocalDateTime[]{startOfDay, meetings.get(0).getStartTime()});
        }

        //check for the after last meeting time slots
        if (Duration.between(meetings.get(meetings.size() - 1).getEndTime(), endOfDay).compareTo(duration) >= 0) {
            freeSlots.add(new LocalDateTime[]{meetings.get(meetings.size() - 1).getEndTime(), endOfDay});
        }

        return freeSlots;
    }
}
