package com.msproject.companyms.clients;

import com.msproject.companyms.external.Jobs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "JOBMS")
public interface JobClient {

    @GetMapping("/jobs/getjobsofonecompany")
    List<Jobs> getJobs(@RequestParam("companyId") Long companyId);

    @DeleteMapping("/jobs/deleteonejob/{jobid}")
    String deleteJobs(@PathVariable Long jobid);
}
