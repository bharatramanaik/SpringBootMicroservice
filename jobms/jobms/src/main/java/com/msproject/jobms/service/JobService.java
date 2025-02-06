package com.msproject.jobms.service;

import com.msproject.jobms.dto.JobDTO;
import com.msproject.jobms.model.Jobs;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface JobService {
    ResponseEntity<List<JobDTO>> findAll();
    ResponseEntity<List<Jobs>> findJobsOfOneCompany(Long companyId);
    ResponseEntity<String> createJob(Jobs jobs);
    ResponseEntity<JobDTO> getJobById(Long id);
    ResponseEntity<String> deleteJobById(Long id);
    ResponseEntity<String> updateJob(Jobs jobs);

}
