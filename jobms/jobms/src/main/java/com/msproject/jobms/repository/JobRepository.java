package com.msproject.jobms.repository;

import com.msproject.jobms.model.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JobRepository extends JpaRepository<Jobs, Long> {
    List<Jobs> findByCompanyId(Long companyId);
}
