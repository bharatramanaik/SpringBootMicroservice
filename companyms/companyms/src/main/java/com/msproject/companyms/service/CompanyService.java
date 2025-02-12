package com.msproject.companyms.service;

import com.msproject.companyms.dto.CompanyDTO;
import com.msproject.companyms.dto.CompanyResponse;
import com.msproject.companyms.dto.MessageResponse;
import com.msproject.companyms.dto.ReviewMessage;
import com.msproject.companyms.model.Company;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyService {
    ResponseEntity<List<CompanyDTO>> findAll();
    ResponseEntity<MessageResponse> createCompany(Company company);
    ResponseEntity<CompanyDTO> getCompanyById(Long id);
    ResponseEntity<MessageResponse> deleteCompanyById(Long id);
    ResponseEntity<MessageResponse> updateCompany(Company company);
    void updateCompanyRating(ReviewMessage reviewMessage);
}
