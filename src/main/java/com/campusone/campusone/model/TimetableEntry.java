package com.campusone.campusone.model;

import jakarta.persistence.*;

@Entity
@Table(name = "timetable_entries")
public class TimetableEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String timeRange; // e.g. "09:00 - 10:00"
    private String subject;   // e.g. "Data Structures"
    private String facultyName; // e.g. "Dr. Smith"
    private String room;      // e.g. "C101"
    private String entryDate; // e.g. "2025-06-12"
    private String dayOfWeek; // e.g. "Thursday"
    private String studentRoleTarget; // e.g. "STUDENT"

    public TimetableEntry() {
    }

    public TimetableEntry(String timeRange, String subject, String facultyName, String room, String entryDate, String dayOfWeek) {
        this.timeRange = timeRange;
        this.subject = subject;
        this.facultyName = facultyName;
        this.room = room;
        this.entryDate = entryDate;
        this.dayOfWeek = dayOfWeek;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStudentRoleTarget() {
        return studentRoleTarget;
    }

    public void setStudentRoleTarget(String studentRoleTarget) {
        this.studentRoleTarget = studentRoleTarget;
    }
}
