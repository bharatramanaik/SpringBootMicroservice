package com.msproject.reviewms.service;

import com.msproject.reviewms.model.Reviews;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {
    ResponseEntity<List<Reviews>> getall();
    ResponseEntity<String> create(Reviews review);
    ResponseEntity<Reviews> getone(Long id);
    ResponseEntity<String> updateone(Reviews review);
    ResponseEntity<String> deleteone(Long id);
}
