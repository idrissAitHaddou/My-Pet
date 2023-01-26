package com.example.cvtheque.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OfferRequest {
    private Long id;
    @NotNull(message = "title is required")
    private String title;
    @NotNull(message = "campany is required")
    private String campany;
    @NotNull(message = "description is required")
    private String description;
    private Boolean accepted = false;
    @NotNull(message = "email current user is required")
    private String emailCurrentuser;
    @NotNull(message = "image is required")
    private List<String> imagesBase = new ArrayList<>();
}
