package com.msproject.reviewms.mapper;

import com.msproject.reviewms.dto.MessageResponse;


public class ReviewResponseMapper {
    public static MessageResponse mapToMessageResponse(String message, int status){
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage(message);
        messageResponse.setStatus(status);
        return messageResponse;
    }
}
