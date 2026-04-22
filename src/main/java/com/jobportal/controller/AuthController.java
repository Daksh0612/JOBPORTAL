package com.jobportal.controller;

import com.jobportal.model.Role;
import com.jobportal.service.AuthService;
import com.jobportal.web.dto.RegistrationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        model.addAttribute("roles", new Role[]{Role.ROLE_CANDIDATE, Role.ROLE_RECRUITER});
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("registrationRequest") RegistrationRequest request,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        model.addAttribute("roles", new Role[]{Role.ROLE_CANDIDATE, Role.ROLE_RECRUITER});

        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            authService.registerUser(request);
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "register";
        }

        redirectAttributes.addFlashAttribute("successMessage", "Registration successful. Please login.");
        return "redirect:/login";
    }
}
