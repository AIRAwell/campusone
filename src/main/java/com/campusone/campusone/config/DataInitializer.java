package com.campusone.campusone.config;

import com.campusone.campusone.model.*;
import com.campusone.campusone.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TimetableRepository timetableRepository;
    private final AnnouncementRepository announcementRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final AssignmentRepository assignmentRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           TimetableRepository timetableRepository,
                           AnnouncementRepository announcementRepository,
                           LeaveRequestRepository leaveRequestRepository,
                           AssignmentRepository assignmentRepository,
                           DepartmentRepository departmentRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.timetableRepository = timetableRepository;
        this.announcementRepository = announcementRepository;
        this.leaveRequestRepository = leaveRequestRepository;
        this.assignmentRepository = assignmentRepository;
        this.departmentRepository = departmentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            seedUsers();
            seedTimetable();
            seedAnnouncements();
            seedLeaveRequests();
            seedAssignments();
            seedDepartments();
        }
    }

    private void seedUsers() {
        // Default users matching Screen 1 login instructions
        userRepository.save(new User("student1", passwordEncoder.encode("student123"), "student1", "student1@campusone.edu", Role.STUDENT, "Computer Science"));
        userRepository.save(new User("faculty1", passwordEncoder.encode("faculty123"), "faculty1", "faculty1@campusone.edu", Role.FACULTY, "Computer Science"));
        userRepository.save(new User("hod1", passwordEncoder.encode("hod123"), "hod1", "hod1@campusone.edu", Role.HOD, "Computer Science"));
        userRepository.save(new User("admin1", passwordEncoder.encode("admin123"), "admin1", "admin1@campusone.edu", Role.ADMIN, "Administration"));

        // Additional sample users for metrics
        userRepository.save(new User("johndoe", passwordEncoder.encode("password123"), "John Doe", "john@campusone.edu", Role.STUDENT, "Computer Science"));
        userRepository.save(new User("priyas", passwordEncoder.encode("password123"), "Priya S", "priya@campusone.edu", Role.STUDENT, "Computer Science"));
        userRepository.save(new User("rahulk", passwordEncoder.encode("password123"), "Rahul K", "rahul@campusone.edu", Role.STUDENT, "Computer Science"));

        userRepository.save(new User("drsmith", passwordEncoder.encode("password123"), "Dr. Smith", "smith@campusone.edu", Role.FACULTY, "Computer Science"));
        userRepository.save(new User("profjohnson", passwordEncoder.encode("password123"), "Prof. Johnson", "johnson@campusone.edu", Role.FACULTY, "Computer Science"));
        userRepository.save(new User("drtaylor", passwordEncoder.encode("password123"), "Dr. Taylor", "taylor@campusone.edu", Role.FACULTY, "Computer Science"));
    }

    private void seedTimetable() {
        // Matches Screen 2 & Screen 6 exactly
        timetableRepository.save(new TimetableEntry("09:00 - 10:00", "Data Structures", "Dr. Smith", "C101", "2025-06-12", "Thursday"));
        timetableRepository.save(new TimetableEntry("10:15 - 11:15", "Web Technology", "Prof. Johnson", "C102", "2025-06-12", "Thursday"));
        timetableRepository.save(new TimetableEntry("11:30 - 12:30", "Database Systems", "Dr. Taylor", "C103", "2025-06-12", "Thursday"));
        timetableRepository.save(new TimetableEntry("14:00 - 15:00", "Operating Systems", "Dr. Miller", "Lab 2", "2025-06-12", "Thursday"));
    }

    private void seedAnnouncements() {
        // Announcements from Screen 7 & Screen 2
        announcementRepository.save(new Announcement("College will remain closed on Friday", "Admin", "2 hours ago", "RED"));
        announcementRepository.save(new Announcement("Technical Fest 2025 - Registrations Open", "Faculty", "1 day ago", "YELLOW"));
        announcementRepository.save(new Announcement("Assignment 2 is uploaded", "Faculty", "2 days ago", "GREEN"));
        announcementRepository.save(new Announcement("Department meeting at 3 PM", "HOD", "3 days ago", "PURPLE"));

        // Faculty announcements
        announcementRepository.save(new Announcement("Lab session cancelled tomorrow", "Faculty", "1 hour ago", "RED"));
        announcementRepository.save(new Announcement("Project submission deadline extended", "Faculty", "5 hours ago", "YELLOW"));

        // Admin announcements
        announcementRepository.save(new Announcement("College Annual Day", "Admin", "1 day ago", "GREEN"));
        announcementRepository.save(new Announcement("Exam Schedule Released", "Admin", "2 days ago", "PURPLE"));
        announcementRepository.save(new Announcement("New Library Rules", "Admin", "3 days ago", "YELLOW"));
    }

    private void seedLeaveRequests() {
        // Matches Screen 8 (Leave/OD Requests Page) and Screen 4 (HOD Dashboard)
        leaveRequestRepository.save(new LeaveRequest("student1", "student1", "2025-06-15", "Leave", "Personal", "Pending"));
        leaveRequestRepository.save(new LeaveRequest("student1", "student1", "2025-06-10", "OD", "Medical", "Approved"));
        leaveRequestRepository.save(new LeaveRequest("student1", "student1", "2025-06-05", "Leave", "Family Function", "Rejected"));

        leaveRequestRepository.save(new LeaveRequest("johndoe", "John Doe", "2025-06-14", "Leave", "Personal", "Pending"));
        leaveRequestRepository.save(new LeaveRequest("priyas", "Priya S", "2025-06-13", "OD", "Medical", "Pending"));
        leaveRequestRepository.save(new LeaveRequest("rahulk", "Rahul K", "2025-06-11", "Leave", "Sports Meet", "Approved"));
    }

    private void seedAssignments() {
        assignmentRepository.save(new Assignment("Assignment 1 - Trees & Graphs", "Data Structures", "2025-06-18", "Dr. Smith", "Pending"));
        assignmentRepository.save(new Assignment("Assignment 2 - Responsive UI", "Web Technology", "2025-06-20", "Prof. Johnson", "Pending"));
        assignmentRepository.save(new Assignment("Lab Report - SQL Queries", "Database Systems", "2025-06-22", "Dr. Taylor", "Pending"));
        assignmentRepository.save(new Assignment("Case Study - Process Synchronization", "Operating Systems", "2025-06-25", "Dr. Miller", "Pending"));
        assignmentRepository.save(new Assignment("Assignment 0 - Basics", "Data Structures", "2025-05-30", "Dr. Smith", "Submitted"));
    }

    private void seedDepartments() {
        departmentRepository.save(new Department("Computer Science & Engineering", "CSE", "hod1", 6, 120));
        departmentRepository.save(new Department("Information Technology", "IT", "Dr. Robert", 4, 90));
        departmentRepository.save(new Department("Electronics & Communication", "ECE", "Dr. Sarah", 5, 110));
        departmentRepository.save(new Department("Mechanical Engineering", "MECH", "Dr. Anderson", 4, 80));
        departmentRepository.save(new Department("Civil Engineering", "CIVIL", "Dr. Wilson", 3, 60));
    }
}
