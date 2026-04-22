package com.jobportal.controller;

import com.jobportal.model.Job;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public HomeController(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        List<Job> featuredJobs = jobRepository.findByActiveTrueOrderByPostedAtDesc();
        model.addAttribute("featuredJobs", featuredJobs.size() > 6 ? featuredJobs.subList(0, 6) : featuredJobs);

        if (authentication != null) {
            userRepository.findByEmail(authentication.getName()).ifPresent(user -> model.addAttribute("currentUser", user));
        }

        return "index";
    }
}
