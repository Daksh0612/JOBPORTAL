package com.jobportal.service;

import com.jobportal.model.Job;
import com.jobportal.model.JobApplication;
import com.jobportal.model.User;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.web.dto.ApplicationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public void apply(User candidate, Job job, ApplicationRequest request) {
        if (applicationRepository.findByCandidateAndJob(candidate, job).isPresent()) {
            throw new IllegalArgumentException("You have already applied for this job");
        }

        JobApplication application = new JobApplication();
        application.setCandidate(candidate);
        application.setJob(job);
        application.setCoverLetter(request.getCoverLetter());
        application.setResumeLink(request.getResumeLink());
        applicationRepository.save(application);
    }

    public List<JobApplication> getCandidateApplications(User candidate) {
        return applicationRepository.findByCandidateOrderByAppliedAtDesc(candidate);
    }

    public List<JobApplication> getRecruiterApplications(User recruiter) {
        return applicationRepository.findByJobPostedByOrderByAppliedAtDesc(recruiter);
    }
}
