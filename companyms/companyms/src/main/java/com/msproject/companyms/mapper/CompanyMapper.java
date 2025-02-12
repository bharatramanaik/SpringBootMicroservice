package com.msproject.companyms.mapper;

import com.msproject.companyms.dto.CompanyDTO;
import com.msproject.companyms.dto.CompanyResponse;
import com.msproject.companyms.dto.MessageResponse;
import com.msproject.companyms.external.Jobs;
import com.msproject.companyms.external.Review;
import com.msproject.companyms.model.Company;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.util.List;

public class CompanyMapper {
    public static CompanyDTO mapToCompanyDTO(Company company, List<Jobs> jobs, List<Review> review){
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setCompanyId(company.getCompanyId());
        companyDTO.setName(company.getName());
        companyDTO.setDescription(company.getDescription());
        companyDTO.setRating(company.getRating());
        companyDTO.setReviews(review);
        companyDTO.setJobs(jobs);
        return companyDTO;
    }

    public static CompanyResponse mapToCompanyResponse(CompanyDTO data, String message, int status){
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setData(data);
        companyResponse.setMessage(message);
        companyResponse.setStatus(status);
        return companyResponse;
    }

    public static MessageResponse mapToMessageResponse(String message, int status){
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage(message);
        messageResponse.setStatus(status);
        return messageResponse;
    }
}
