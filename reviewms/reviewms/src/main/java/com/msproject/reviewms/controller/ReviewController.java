package com.msproject.reviewms.controller;

import com.msproject.reviewms.model.Reviews;
import com.msproject.reviewms.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/getallreviews")
    public ResponseEntity<List<Reviews>> getall(){
        return reviewService.getall();
    }

    @PostMapping("/createreview")
    public ResponseEntity<String> createreview(@RequestBody Reviews review){
        return reviewService.create(review);
    }

    @GetMapping("/getonereview/{id}")
    public ResponseEntity<Reviews> getonereview(@PathVariable Long id){
        return reviewService.getone(id);
    }

    @PutMapping("/updatereview")
    public ResponseEntity<String> updatereview(@RequestBody Reviews review){
        return reviewService.updateone(review);
    }

    @DeleteMapping("/deleteonereview/{id}")
    public ResponseEntity<String> deleteonereview(@PathVariable Long id){
        return reviewService.deleteone(id);
    }
}
