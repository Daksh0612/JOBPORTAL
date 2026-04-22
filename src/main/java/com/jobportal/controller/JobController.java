package com.jobportal.controller;

import com.jobportal.model.Job;
import com.jobportal.model.User;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.ApplicationService;
import com.jobportal.service.JobService;
import com.jobportal.web.dto.ApplicationRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class JobController {

    private final JobService jobService;
    private final ApplicationService applicationService;
    private final UserRepository userRepository;

    public JobController(JobService jobService, ApplicationService applicationService, UserRepository userRepository) {
        this.jobService = jobService;
        this.applicationService = applicationService;
        this.userRepository = userRepository;
    }

    @GetMapping("/jobs")
    public String jobs(Model model) {
        model.addAttribute("jobs", jobService.getActiveJobs());
        return "jobs";
    }

    @GetMapping("/jobs/{id}")
    public String jobDetails(@PathVariable Long id, Model model) {
        model.addAttribute("job", jobService.getJobById(id));
        model.addAttribute("applicationRequest", new ApplicationRequest());
        return "job-details";
    }

    @PostMapping("/jobs/{id}/apply")
    public String apply(@PathVariable Long id,
                        @Valid @ModelAttribute("applicationRequest") ApplicationRequest request,
                        BindingResult bindingResult,
                        Authentication authentication,
                        RedirectAttributes redirectAttributes,
                        Model model) {
        Job job = jobService.getJobById(id);

        if (bindingResult.hasErrors()) {
            model.addAttribute("job", job);
            return "job-details";
        }

        User user = userRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        try {
            applicationService.apply(user, job, request);
            redirectAttributes.addFlashAttribute("successMessage", "Application submitted successfully.");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }

        return "redirect:/jobs/" + id;
    }
}
