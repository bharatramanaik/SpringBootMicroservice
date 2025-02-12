package com.msproject.companyms.service;

import com.msproject.companyms.dto.CompanyDTO;
import com.msproject.companyms.dto.CompanyResponse;
import com.msproject.companyms.dto.ReviewMessage;
import com.msproject.companyms.model.Company;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyService {
    ResponseEntity<List<CompanyDTO>> findAll();
    ResponseEntity<String> createCompany(Company company);
    ResponseEntity<CompanyResponse> getCompanyById(Long id);
    ResponseEntity<String> deleteCompanyById(Long id);
    ResponseEntity<String> updateCompany(Company company);
    void updateCompanyRating(ReviewMessage reviewMessage);
}
