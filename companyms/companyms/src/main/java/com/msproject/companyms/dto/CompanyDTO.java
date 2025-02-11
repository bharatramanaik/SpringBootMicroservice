package com.msproject.companyms.dto;

import com.msproject.companyms.external.Jobs;
import com.msproject.companyms.external.Review;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * This class is for merging the responses i.e., Company object with Job and Review objects
 * */
@Getter
@Setter
public class CompanyDTO {
    private Long companyId;
    private String name;
    private String description;
    private Double rating;
    private List<Review> reviews;
    private List<Jobs> jobs;
}
