package com.msproject.jobms.repository;

import com.msproject.jobms.model.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;



public interface JobRepository extends JpaRepository<Jobs, Long> {
}
