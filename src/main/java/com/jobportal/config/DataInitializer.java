package com.jobportal.config;

import com.jobportal.model.Job;
import com.jobportal.model.Role;
import com.jobportal.model.User;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, JobRepository jobRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) {
            return;
        }

        User recruiter = new User();
        recruiter.setFullName("Portal Recruiter");
        recruiter.setEmail("recruiter@jobportal.com");
        recruiter.setPassword(passwordEncoder.encode("Recruiter@123"));
        recruiter.setRole(Role.ROLE_RECRUITER);
        recruiter.setCompanyName("LaunchHire");
        userRepository.save(recruiter);

        User candidate = new User();
        candidate.setFullName("Portal Candidate");
        candidate.setEmail("candidate@jobportal.com");
        candidate.setPassword(passwordEncoder.encode("Candidate@123"));
        candidate.setRole(Role.ROLE_CANDIDATE);
        userRepository.save(candidate);

        Job firstJob = new Job();
        firstJob.setTitle("Java Spring Boot Developer");
        firstJob.setCompany("LaunchHire");
        firstJob.setLocation("Ahmedabad");
        firstJob.setType("Full Time");
        firstJob.setSalary("6-10 LPA");
        firstJob.setSkills("Java, Spring Boot, MySQL, REST APIs");
        firstJob.setDescription("Build backend services, work with MVC architecture, and deliver production-ready APIs.");
        firstJob.setPostedBy(recruiter);

        Job secondJob = new Job();
        secondJob.setTitle("Frontend Developer");
        secondJob.setCompany("LaunchHire");
        secondJob.setLocation("Remote");
        secondJob.setType("Internship");
        secondJob.setSalary("15,000/month");
        secondJob.setSkills("HTML, CSS, JavaScript, Bootstrap");
        secondJob.setDescription("Create clean, responsive screens and collaborate closely with backend developers.");
        secondJob.setPostedBy(recruiter);

        jobRepository.saveAll(Arrays.asList(firstJob, secondJob));
    }
}
