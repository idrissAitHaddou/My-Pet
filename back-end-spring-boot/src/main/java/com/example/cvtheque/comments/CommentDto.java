package com.example.cvtheque.comments;

import com.example.cvtheque.offers.OfferEntity;
import com.example.cvtheque.users.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonMerge;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import org.springframework.stereotype.Component;

@Component
public class CommentDto {
    private Long id;
    private String comment;
    private String created_at;
    private OfferEntity job = new OfferEntity();
    private UserEntity user = new UserEntity();
}
