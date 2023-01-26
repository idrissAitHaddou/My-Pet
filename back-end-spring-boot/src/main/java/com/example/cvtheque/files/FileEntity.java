package com.example.cvtheque.files;

import com.example.cvtheque.offers.OfferEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    @Column(columnDefinition = "text")
    private String src;
    @JsonIgnore
    @ManyToOne
    private OfferEntity job;
}
