package com.msproject.companyms.service;

import com.msproject.companyms.model.Company;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyService {
    ResponseEntity<List<Company>> findAll();
    ResponseEntity<String> createCompany(Company company);
    ResponseEntity<Company> getCompanyById(Long id);
    ResponseEntity<String> deleteCompanyById(Long id);
    ResponseEntity<String> updateCompany(Company company);
}
