package com.campusone.campusone.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/")
    public String index(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            for (var authority : authentication.getAuthorities()) {
                String auth = authority.getAuthority();
                if ("ROLE_STUDENT".equals(auth)) return "redirect:/student/dashboard";
                if ("ROLE_FACULTY".equals(auth)) return "redirect:/faculty/dashboard";
                if ("ROLE_HOD".equals(auth)) return "redirect:/hod/dashboard";
                if ("ROLE_ADMIN".equals(auth)) return "redirect:/admin/dashboard";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
