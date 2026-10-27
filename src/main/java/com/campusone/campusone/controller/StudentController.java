package com.campusone.campusone.controller;

import com.campusone.campusone.model.LeaveRequest;
import com.campusone.campusone.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final TimetableRepository timetableRepository;
    private final AnnouncementRepository announcementRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;

    public StudentController(TimetableRepository timetableRepository,
                             AnnouncementRepository announcementRepository,
                             LeaveRequestRepository leaveRequestRepository,
                             AssignmentRepository assignmentRepository,
                             UserRepository userRepository) {
        this.timetableRepository = timetableRepository;
        this.announcementRepository = announcementRepository;
        this.leaveRequestRepository = leaveRequestRepository;
        this.assignmentRepository = assignmentRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "student1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Student");
        model.addAttribute("activePage", "dashboard");

        // Screen 2 Stats
        model.addAttribute("todayClassesCount", 3);
        model.addAttribute("attendancePercentage", "85%");
        model.addAttribute("pendingAssignmentsCount", 2);
        model.addAttribute("newNoticesCount", 3);

        // Screen 2 Tables & Lists
        model.addAttribute("todayTimetable", timetableRepository.findAll().stream().limit(3).toList());
        model.addAttribute("recentAnnouncements", announcementRepository.findTop3ByOrderByIdDesc());

        return "student/dashboard";
    }

    @GetMapping("/timetable")
    public String timetable(@RequestParam(value = "date", required = false, defaultValue = "2025-06-12") String date,
                            Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "student1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Student");
        model.addAttribute("activePage", "timetable");
        model.addAttribute("selectedDate", date);
        model.addAttribute("timetableList", timetableRepository.findAll());
        return "student/timetable";
    }

    @GetMapping("/announcements")
    public String announcements(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "student1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Student");
        model.addAttribute("activePage", "announcements");
        model.addAttribute("announcements", announcementRepository.findAllByOrderByIdDesc());
        return "student/announcements";
    }

    @GetMapping("/leave-requests")
    public String leaveRequests(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "student1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Student");
        model.addAttribute("activePage", "leave-requests");
        model.addAttribute("requests", leaveRequestRepository.findByStudentUsernameOrderByIdDesc(username));
        return "student/leave-requests";
    }

    @PostMapping("/leave-requests")
    public String submitLeaveRequest(@RequestParam("date") String date,
                                     @RequestParam("type") String type,
                                     @RequestParam("reason") String reason,
                                     Principal principal) {
        String username = principal != null ? principal.getName() : "student1";
        LeaveRequest req = new LeaveRequest(username, username, date, type, reason, "Pending");
        leaveRequestRepository.save(req);
        return "redirect:/student/leave-requests?success=true";
    }

    @GetMapping("/attendance")
    public String attendance(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "student1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Student");
        model.addAttribute("activePage", "attendance");
        model.addAttribute("attendancePercentage", "85%");
        return "student/attendance";
    }

    @GetMapping("/assignments")
    public String assignments(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "student1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Student");
        model.addAttribute("activePage", "assignments");
        model.addAttribute("assignments", assignmentRepository.findAll());
        return "student/assignments";
    }

    @GetMapping("/notes")
    public String notes(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "student1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Student");
        model.addAttribute("activePage", "notes");
        return "student/notes";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "student1";
        var user = userRepository.findByUsername(username).orElse(null);
        model.addAttribute("username", username);
        model.addAttribute("role", "Student");
        model.addAttribute("activePage", "profile");
        model.addAttribute("user", user);
        return "student/profile";
    }
}
