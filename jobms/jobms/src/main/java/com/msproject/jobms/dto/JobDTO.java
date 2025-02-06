package com.msproject.jobms.dto;

import com.msproject.jobms.external.Company;
import com.msproject.jobms.external.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * This class is for merging the job object with the respective company
 * */
@Getter
@Setter
public class JobDTO {
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company;
    private List<Review> review;


}
