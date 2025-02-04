package com.msproject.jobms.service.imple;

import com.msproject.jobms.model.Jobs;
import com.msproject.jobms.repository.JobRepository;
import com.msproject.jobms.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public ResponseEntity<List<Jobs>> findAll() {
        return new ResponseEntity<>(jobRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createJob(Jobs jobs) {
        jobRepository.save(jobs);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Jobs> getJobById(Long id) {
        Jobs jobs = jobRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));

        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteJobById(Long id) {
        Jobs oldjob = jobRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found")
        );
        jobRepository.deleteById(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateJob(Jobs jobs) {
        Long jobid = jobs.getId();
        Jobs oldjob = jobRepository.findById(jobid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
        jobRepository.save(jobs);
        return new ResponseEntity<>("updated", HttpStatus.OK);
    }
}
