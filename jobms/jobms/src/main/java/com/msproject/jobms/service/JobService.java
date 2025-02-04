package com.msproject.jobms.service;

import com.msproject.jobms.model.Jobs;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface JobService {
    ResponseEntity<List<Jobs>> findAll();
    ResponseEntity<String> createJob(Jobs jobs);
    ResponseEntity<Jobs> getJobById(Long id);
    ResponseEntity<String> deleteJobById(Long id);
    ResponseEntity<String> updateJob(Jobs jobs);

}
