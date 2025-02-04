package com.msproject.companyms.service.imple;

import com.msproject.companyms.model.Company;
import com.msproject.companyms.repository.CompanyRepository;
import com.msproject.companyms.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public ResponseEntity<List<Company>> findAll() {
        return new ResponseEntity<>(companyRepository.findAll(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createCompany(Company company) {
        companyRepository.save(company);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Company> getCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found")
        );
        companyRepository.delete(company);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateCompany(Company company) {
        Long compid = company.getCompanyId();
        Company oldcompany = companyRepository.findById(compid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found")
        );
        companyRepository.save(company);
        return new ResponseEntity<>("updated", HttpStatus.OK);
    }
}
