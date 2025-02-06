package com.msproject.reviewms.service;

import com.msproject.reviewms.model.Reviews;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {
    ResponseEntity<List<Reviews>> getall(Long companyid);
    ResponseEntity<String> create(Reviews review);
    ResponseEntity<Reviews> getone(Long reviewid);
    ResponseEntity<String> updateone(Reviews review);
    ResponseEntity<String> deleteone(Long reviewid);
}
