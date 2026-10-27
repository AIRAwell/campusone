package com.campusone.campusone.controller;

import com.campusone.campusone.model.Announcement;
import com.campusone.campusone.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/faculty")
public class FacultyController {

    private final TimetableRepository timetableRepository;
    private final AnnouncementRepository announcementRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;

    public FacultyController(TimetableRepository timetableRepository,
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
        String username = principal != null ? principal.getName() : "faculty1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Faculty");
        model.addAttribute("activePage", "dashboard");

        // Screen 3 Stats
        model.addAttribute("myClassesCount", 4);
        model.addAttribute("pendingAssignmentsCount", 5);
        model.addAttribute("newRequestsCount", 2);

        // Screen 3 Announcements
        model.addAttribute("recentAnnouncements", announcementRepository.findTop3ByOrderByIdDesc());

        return "faculty/dashboard";
    }

    @GetMapping("/mark-attendance")
    public String markAttendance(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "faculty1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Faculty");
        model.addAttribute("activePage", "mark-attendance");
        return "faculty/mark-attendance";
    }

    @GetMapping("/upload-notes")
    public String uploadNotes(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "faculty1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Faculty");
        model.addAttribute("activePage", "upload-notes");
        return "faculty/upload-notes";
    }

    @GetMapping("/enter-marks")
    public String enterMarks(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "faculty1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Faculty");
        model.addAttribute("activePage", "enter-marks");
        return "faculty/enter-marks";
    }

    @GetMapping("/announcements")
    public String announcements(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "faculty1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Faculty");
        model.addAttribute("activePage", "announcements");
        model.addAttribute("announcements", announcementRepository.findAllByOrderByIdDesc());
        return "faculty/announcements";
    }

    @PostMapping("/announcements")
    public String postAnnouncement(@RequestParam("title") String title,
                                   @RequestParam(value = "colorTag", defaultValue = "BLUE") String colorTag,
                                   Principal principal) {
        String username = principal != null ? principal.getName() : "faculty1";
        Announcement a = new Announcement(title, "Faculty", username, "Just now", colorTag);
        announcementRepository.save(a);
        return "redirect:/faculty/announcements?posted=true";
    }

    @GetMapping("/requests")
    public String requests(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "faculty1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Faculty");
        model.addAttribute("activePage", "requests");
        model.addAttribute("requests", leaveRequestRepository.findAllByOrderByIdDesc());
        return "faculty/requests";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "faculty1";
        var user = userRepository.findByUsername(username).orElse(null);
        model.addAttribute("username", username);
        model.addAttribute("role", "Faculty");
        model.addAttribute("activePage", "profile");
        model.addAttribute("user", user);
        return "faculty/profile";
    }
}
