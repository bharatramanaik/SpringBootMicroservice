package com.msproject.companyms.mapper;

import com.msproject.companyms.dto.CompanyDTO;
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
        companyDTO.setReviews(review);
        companyDTO.setJobs(jobs);
        return companyDTO;
    }
}
