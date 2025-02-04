package com.msproject.reviewms.repository;

import com.msproject.reviewms.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Reviews, Long> {
}
