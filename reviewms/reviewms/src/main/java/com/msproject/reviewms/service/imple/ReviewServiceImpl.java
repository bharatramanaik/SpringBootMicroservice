package com.msproject.reviewms.service.imple;

import com.msproject.reviewms.clients.CompanyClient;
import com.msproject.reviewms.external.Company;
import com.msproject.reviewms.model.Reviews;
import com.msproject.reviewms.repository.ReviewRepository;
import com.msproject.reviewms.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    private CompanyClient companyClient;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ResponseEntity<List<Reviews>> getall(Long companyId) {
        List<Reviews> reviews = reviewRepository.findByCompanyId(companyId);
        return new ResponseEntity<>(reviewRepository.findByCompanyId(companyId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> create(Reviews review) {
        Company company = companyClient.getCompany(review.getCompanyId());
        if (company == null){
            return new ResponseEntity<>("Company not exists", HttpStatus.NOT_FOUND);
        }
        reviewRepository.save(review);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Reviews> getone(Long reviewId) {
        Reviews review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found")
        );
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateone(Reviews review) {
        Long rid = review.getId();
        Reviews oldreview = reviewRepository.findById(rid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found")
        );
        reviewRepository.save(review);
        return new ResponseEntity<>("updated", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteone(Long reviewId) {
        Reviews oldreview = reviewRepository.findById(reviewId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found")
        );
        reviewRepository.delete(oldreview);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
