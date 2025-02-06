package com.msproject.companyms.clients;

import com.msproject.companyms.external.Jobs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "JOBMS")
public interface JobClient {

    @GetMapping("/jobs/getjobsofonecompany")
    List<Jobs> getJobs(@RequestParam("companyId") Long companyId);
}
