package com.msproject.jobms.mapper;

import com.msproject.jobms.dto.JobDTO;
import com.msproject.jobms.dto.MessageResponse;
import com.msproject.jobms.external.Company;
import com.msproject.jobms.external.Review;
import com.msproject.jobms.model.Jobs;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobDTO(Jobs job, Company company, List<Review> review){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setCompany(company);
        jobDTO.setReview(review);

        return jobDTO;
    }

    public static MessageResponse mapToMessageResponse(String message, int status){
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage(message);
        messageResponse.setStatus(status);
        return messageResponse;
    }
}
