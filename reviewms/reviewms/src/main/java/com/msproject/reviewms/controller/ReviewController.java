package com.msproject.reviewms.controller;

import com.msproject.reviewms.messaging.ReviewMessageProducer;
import com.msproject.reviewms.model.Reviews;
import com.msproject.reviewms.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;
    private ReviewMessageProducer reviewMessageProducer;

    public ReviewController(ReviewService reviewService, ReviewMessageProducer reviewMessageProducer) {
        this.reviewService = reviewService;
        this.reviewMessageProducer = reviewMessageProducer;
    }

    @GetMapping("/getallreviews")
    public ResponseEntity<List<Reviews>> getall(@RequestParam Long companyId){
        return reviewService.getall(companyId);
    }

    @PostMapping("/createreview")
    public ResponseEntity<String> createreview(@RequestBody Reviews review){
        reviewMessageProducer.sendMessage(review);
        return reviewService.create(review);
    }

    @GetMapping("/getonereview/{reviewid}")
    public ResponseEntity<Reviews> getonereview(@PathVariable Long reviewid){
        return reviewService.getone(reviewid);
    }

    @GetMapping("/getaveragerating")
    public Double getAverageRating(@RequestParam Long companyId){
        List<Reviews> reviews = reviewService.getall(companyId).getBody();
        return reviews.stream().mapToDouble(Reviews::getRating).average().orElse(0.0);
    }

    @PutMapping("/updatereview")
    public ResponseEntity<String> updatereview(@RequestBody Reviews review){
        return reviewService.updateone(review);
    }

    @DeleteMapping("/deleteonereview/{reviewid}")
    public ResponseEntity<String> deleteonereview(@PathVariable Long reviewid){
        return reviewService.deleteone(reviewid);
    }
}
