package com.msproject.jobms.controller;

import com.msproject.jobms.dto.JobDTO;
import com.msproject.jobms.dto.MessageResponse;
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
    public ResponseEntity<List<JobDTO>> findAlljobs(){
        return jobService.findAll();
    }

    @GetMapping("/getjobsofonecompany")
    public ResponseEntity<List<Jobs>> findJobsofOneCompany(@RequestParam Long companyId){
        return jobService.findJobsOfOneCompany(companyId);
    }

    @PostMapping("/createjob")
    public ResponseEntity<MessageResponse> createJob(@RequestBody Jobs job){
        return jobService.createJob(job);
    }

    @GetMapping("/getonejob/{jobid}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long jobid){
        return jobService.getJobById(jobid);
    }

    @PutMapping("/updatejob")
    public ResponseEntity<MessageResponse> updateJob(@RequestBody Jobs job){
        return jobService.updateJob(job);
    }

    @DeleteMapping("/deleteonejob/{jobid}")
    public ResponseEntity<MessageResponse> deleteJob(@PathVariable Long jobid){
        return jobService.deleteJobById(jobid);
    }

}
