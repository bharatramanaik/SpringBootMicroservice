package com.msproject.reviewms.messaging;

import com.msproject.reviewms.dto.ReviewMessage;
import com.msproject.reviewms.model.Reviews;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Reviews reviews){
        ReviewMessage reviewMessage = new ReviewMessage();
        reviewMessage.setId(reviews.getId());
        reviewMessage.setTitle(reviews.getTitle());
        reviewMessage.setDescription(reviews.getDescription());
        reviewMessage.setRating(reviews.getRating());
        reviewMessage.setCompanyId(reviews.getCompanyId());
        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage);
    }

}
