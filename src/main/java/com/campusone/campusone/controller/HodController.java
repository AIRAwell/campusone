package com.campusone.campusone.controller;

import com.campusone.campusone.model.LeaveRequest;
import com.campusone.campusone.model.Role;
import com.campusone.campusone.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/hod")
public class HodController {

    private final UserRepository userRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final TimetableRepository timetableRepository;
    private final DepartmentRepository departmentRepository;

    public HodController(UserRepository userRepository,
                         LeaveRequestRepository leaveRequestRepository,
                         TimetableRepository timetableRepository,
                         DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.leaveRequestRepository = leaveRequestRepository;
        this.timetableRepository = timetableRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "hod1";
        model.addAttribute("username", username);
        model.addAttribute("role", "HOD");
        model.addAttribute("activePage", "dashboard");

        // Screen 4 Stats
        model.addAttribute("facultyCount", 6);
        model.addAttribute("studentCount", 120);
        model.addAttribute("pendingApprovalsCount", leaveRequestRepository.countByStatus("Pending"));

        // Screen 4 Recent Requests
        model.addAttribute("recentRequests", leaveRequestRepository.findAllByOrderByIdDesc().stream().limit(3).toList());

        return "hod/dashboard";
    }

    @GetMapping("/reports")
    public String reports(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "hod1";
        model.addAttribute("username", username);
        model.addAttribute("role", "HOD");
        model.addAttribute("activePage", "reports");
        return "hod/reports";
    }

    @GetMapping("/faculty")
    public String manageFaculty(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "hod1";
        model.addAttribute("username", username);
        model.addAttribute("role", "HOD");
        model.addAttribute("activePage", "manage-faculty");
        model.addAttribute("facultyList", userRepository.findByRole(Role.FACULTY));
        return "hod/faculty";
    }

    @GetMapping("/timetable")
    public String manageTimetable(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "hod1";
        model.addAttribute("username", username);
        model.addAttribute("role", "HOD");
        model.addAttribute("activePage", "manage-timetable");
        model.addAttribute("timetableList", timetableRepository.findAll());
        return "hod/timetable";
    }

    @GetMapping("/approve-requests")
    public String approveRequests(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "hod1";
        model.addAttribute("username", username);
        model.addAttribute("role", "HOD");
        model.addAttribute("activePage", "approve-requests");
        model.addAttribute("requests", leaveRequestRepository.findAllByOrderByIdDesc());
        return "hod/approve-requests";
    }

    @PostMapping("/requests/{id}/approve")
    public String approveRequest(@PathVariable("id") Long id) {
        leaveRequestRepository.findById(id).ifPresent(req -> {
            req.setStatus("Approved");
            leaveRequestRepository.save(req);
        });
        return "redirect:/hod/approve-requests?updated=true";
    }

    @PostMapping("/requests/{id}/reject")
    public String rejectRequest(@PathVariable("id") Long id) {
        leaveRequestRepository.findById(id).ifPresent(req -> {
            req.setStatus("Rejected");
            leaveRequestRepository.save(req);
        });
        return "redirect:/hod/approve-requests?updated=true";
    }

    @GetMapping("/attendance-performance")
    public String attendancePerformance(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "hod1";
        model.addAttribute("username", username);
        model.addAttribute("role", "HOD");
        model.addAttribute("activePage", "attendance-performance");
        return "hod/attendance-performance";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "hod1";
        var user = userRepository.findByUsername(username).orElse(null);
        model.addAttribute("username", username);
        model.addAttribute("role", "HOD");
        model.addAttribute("activePage", "profile");
        model.addAttribute("user", user);
        return "hod/profile";
    }
}
