package com.campusone.campusone.controller;

import com.campusone.campusone.model.Announcement;
import com.campusone.campusone.model.Role;
import com.campusone.campusone.model.User;
import com.campusone.campusone.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final AnnouncementRepository announcementRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserRepository userRepository,
                           DepartmentRepository departmentRepository,
                           AnnouncementRepository announcementRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.announcementRepository = announcementRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "admin1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Admin");
        model.addAttribute("activePage", "dashboard");

        // Screen 5 Stats
        model.addAttribute("totalStudentsCount", 85);
        model.addAttribute("totalFacultyCount", 12);
        model.addAttribute("totalDepartmentsCount", 5);

        // Screen 5 Notices
        model.addAttribute("recentNotices", announcementRepository.findTop3ByOrderByIdDesc());

        return "admin/dashboard";
    }

    @GetMapping("/users")
    public String manageUsers(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "admin1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Admin");
        model.addAttribute("activePage", "manage-users");
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }

    @PostMapping("/users")
    public String addUser(@RequestParam("username") String newUsername,
                          @RequestParam("password") String password,
                          @RequestParam("fullName") String fullName,
                          @RequestParam("email") String email,
                          @RequestParam("role") String role,
                          @RequestParam("department") String department) {
        Role userRole = Role.valueOf(role.toUpperCase());
        User user = new User(newUsername, passwordEncoder.encode(password), fullName, email, userRole, department);
        userRepository.save(user);
        return "redirect:/admin/users?created=true";
    }

    @GetMapping("/departments")
    public String departments(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "admin1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Admin");
        model.addAttribute("activePage", "departments");
        model.addAttribute("departments", departmentRepository.findAll());
        return "admin/departments";
    }

    @GetMapping("/courses")
    public String courses(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "admin1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Admin");
        model.addAttribute("activePage", "courses");
        return "admin/courses";
    }

    @GetMapping("/notices")
    public String notices(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "admin1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Admin");
        model.addAttribute("activePage", "notices");
        model.addAttribute("notices", announcementRepository.findAllByOrderByIdDesc());
        return "admin/notices";
    }

    @PostMapping("/notices")
    public String postNotice(@RequestParam("title") String title,
                             @RequestParam(value = "colorTag", defaultValue = "PURPLE") String colorTag,
                             Principal principal) {
        String username = principal != null ? principal.getName() : "admin1";
        Announcement notice = new Announcement(title, "Admin", username, "Just now", colorTag);
        announcementRepository.save(notice);
        return "redirect:/admin/notices?posted=true";
    }

    @GetMapping("/events")
    public String events(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "admin1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Admin");
        model.addAttribute("activePage", "events");
        return "admin/events";
    }

    @GetMapping("/rooms")
    public String rooms(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "admin1";
        model.addAttribute("username", username);
        model.addAttribute("role", "Admin");
        model.addAttribute("activePage", "rooms");
        return "admin/rooms";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        String username = principal != null ? principal.getName() : "admin1";
        var user = userRepository.findByUsername(username).orElse(null);
        model.addAttribute("username", username);
        model.addAttribute("role", "Admin");
        model.addAttribute("activePage", "profile");
        model.addAttribute("user", user);
        return "admin/profile";
    }
}
