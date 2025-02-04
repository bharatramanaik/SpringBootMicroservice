package com.msproject.companyms.repository;

import com.msproject.companyms.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
