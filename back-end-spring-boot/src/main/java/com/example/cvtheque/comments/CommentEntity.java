package com.example.cvtheque.comments;

import com.example.cvtheque.offers.OfferEntity;
import com.example.cvtheque.users.UserEntity;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comment;
    private String created_at;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private OfferEntity job = new OfferEntity();
    @JsonFormat
    @JsonMerge
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user = new UserEntity();
}
