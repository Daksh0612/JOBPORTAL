package com.jobportal.repository;

import com.jobportal.model.Job;
import com.jobportal.model.JobApplication;
import com.jobportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByCandidateOrderByAppliedAtDesc(User candidate);
    List<JobApplication> findByJobPostedByOrderByAppliedAtDesc(User recruiter);
    Optional<JobApplication> findByCandidateAndJob(User candidate, Job job);
}
