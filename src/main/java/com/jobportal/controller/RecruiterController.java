package com.jobportal.controller;

import com.jobportal.model.User;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.ApplicationService;
import com.jobportal.service.JobService;
import com.jobportal.web.dto.JobRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RecruiterController {

    private final UserRepository userRepository;
    private final JobService jobService;
    private final ApplicationService applicationService;

    public RecruiterController(UserRepository userRepository, JobService jobService, ApplicationService applicationService) {
        this.userRepository = userRepository;
        this.jobService = jobService;
        this.applicationService = applicationService;
    }

    @GetMapping("/recruiter/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        User recruiter = userRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        model.addAttribute("currentUser", recruiter);
        model.addAttribute("jobs", jobService.getJobsByRecruiter(recruiter));
        model.addAttribute("applications", applicationService.getRecruiterApplications(recruiter));
        return "recruiter-dashboard";
    }

    @GetMapping("/recruiter/jobs/new")
    public String createJobForm(Model model, Authentication authentication) {
        User recruiter = userRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        JobRequest request = new JobRequest();
        request.setCompany(recruiter.getCompanyName());
        model.addAttribute("jobRequest", request);
        return "job-form";
    }

    @PostMapping("/recruiter/jobs")
    public String createJob(@Valid @ModelAttribute("jobRequest") JobRequest request,
                            BindingResult bindingResult,
                            Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "job-form";
        }

        User recruiter = userRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        jobService.createJob(request, recruiter);
        return "redirect:/recruiter/dashboard";
    }
}
