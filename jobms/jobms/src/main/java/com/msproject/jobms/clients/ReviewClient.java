package com.msproject.jobms.clients;

import com.msproject.jobms.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEWMS")
public interface ReviewClient {

    @GetMapping("/reviews/getallreviews")
    List<Review> getReviews(@RequestParam("companyId") Long companyId);

}
