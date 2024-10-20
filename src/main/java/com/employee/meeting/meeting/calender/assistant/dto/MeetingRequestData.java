package com.employee.meeting.meeting.calender.assistant.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MeetingRequestData {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Long> participantIds;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<Long> getParticipantIds() {
        return participantIds;
    }

    public void setParticipantIds(List<Long> participantIds) {
        this.participantIds = participantIds;
    }

}
