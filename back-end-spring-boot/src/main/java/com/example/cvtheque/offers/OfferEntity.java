package com.example.cvtheque.offers;

import com.example.cvtheque.comments.CommentEntity;
import com.example.cvtheque.files.FileEntity;
import com.example.cvtheque.users.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String campany;
    private String description;
    private String created_at;
    private Boolean accepted = false;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user;
    @JsonIgnore
    @OneToMany(mappedBy = "job",fetch = FetchType.EAGER)
    private List<FileEntity> images = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "job",fetch = FetchType.EAGER)
    private List<CommentEntity> comments = new ArrayList<>();
}
