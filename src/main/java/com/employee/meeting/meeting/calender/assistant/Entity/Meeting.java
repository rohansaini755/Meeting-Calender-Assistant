package com.employee.meeting.meeting.calender.assistant.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Meeting {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    public List<Employee> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Employee> participants) {
        this.participants = participants;
    }

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    private Employee owner;

    @ManyToMany
    private List<Employee> participants;



    // Getters and Setters
}
