package com.msproject.reviewms.service;

import com.msproject.reviewms.dto.MessageResponse;
import com.msproject.reviewms.model.Reviews;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {
    ResponseEntity<List<Reviews>> getall(Long companyid);
    ResponseEntity<MessageResponse> create(Reviews review);
    ResponseEntity<Reviews> getone(Long reviewid);
    ResponseEntity<MessageResponse> updateone(Reviews review);
    ResponseEntity<MessageResponse> deleteone(Long reviewid);
}
