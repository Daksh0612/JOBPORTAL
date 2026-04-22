package com.jobportal.controller;

import com.jobportal.model.User;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.ApplicationService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CandidateController {

    private final UserRepository userRepository;
    private final ApplicationService applicationService;

    public CandidateController(UserRepository userRepository, ApplicationService applicationService) {
        this.userRepository = userRepository;
        this.applicationService = applicationService;
    }

    @GetMapping("/candidate/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        User user = userRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        model.addAttribute("applications", applicationService.getCandidateApplications(user));
        model.addAttribute("currentUser", user);
        return "candidate-dashboard";
    }
}
