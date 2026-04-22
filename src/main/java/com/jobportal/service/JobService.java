package com.jobportal.service;

import com.jobportal.model.Job;
import com.jobportal.model.User;
import com.jobportal.repository.JobRepository;
import com.jobportal.web.dto.JobRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> getActiveJobs() {
        return jobRepository.findByActiveTrueOrderByPostedAtDesc();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Job not found"));
    }

    public List<Job> getJobsByRecruiter(User recruiter) {
        return jobRepository.findByPostedByOrderByPostedAtDesc(recruiter);
    }

    public Job createJob(JobRequest request, User recruiter) {
        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setCompany(request.getCompany());
        job.setLocation(request.getLocation());
        job.setType(request.getType());
        job.setDescription(request.getDescription());
        job.setSkills(request.getSkills());
        job.setSalary(request.getSalary());
        job.setPostedBy(recruiter);
        return jobRepository.save(job);
    }
}
