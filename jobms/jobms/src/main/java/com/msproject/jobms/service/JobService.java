package com.msproject.jobms.service;

import com.msproject.jobms.dto.JobDTO;
import com.msproject.jobms.dto.MessageResponse;
import com.msproject.jobms.model.Jobs;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface JobService {
    ResponseEntity<List<JobDTO>> findAll();
    ResponseEntity<List<Jobs>> findJobsOfOneCompany(Long companyId);
    ResponseEntity<MessageResponse> createJob(Jobs jobs);
    ResponseEntity<JobDTO> getJobById(Long id);
    ResponseEntity<MessageResponse> deleteJobById(Long id);
    ResponseEntity<MessageResponse> updateJob(Jobs jobs);

}
