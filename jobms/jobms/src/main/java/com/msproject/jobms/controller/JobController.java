package com.msproject.jobms.controller;

import com.msproject.jobms.model.Jobs;
import com.msproject.jobms.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/getalljobs")
    public ResponseEntity<List<Jobs>> findAlljobs(){
        return jobService.findAll();
    }

    @PostMapping("/createjob")
    public ResponseEntity<String> createJob(@RequestBody Jobs job){
        return jobService.createJob(job);
    }

    @GetMapping("/getonejob/{jobid}")
    public ResponseEntity<Jobs> getJobById(@PathVariable Long jobid){
        return jobService.getJobById(jobid);
    }

    @PutMapping("/updatejob")
    public ResponseEntity<String> updateJob(@RequestBody Jobs job){
        return jobService.updateJob(job);
    }

    @DeleteMapping("/deleteonejob/{jobid}")
    public ResponseEntity<String> deleteJob(@PathVariable Long jobid){
        return jobService.deleteJobById(jobid);
    }

}
