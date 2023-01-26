package com.example.cvtheque.responces;

import com.example.cvtheque.comments.CommentEntity;
import com.example.cvtheque.files.FileEntity;
import com.example.cvtheque.users.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class OfferResponce {
    private Long id;
    private String title;
    private String campany;
    private String description;
    private String created_at;
    private Boolean accepted = false;
    private UserEntity user;
    private List<FileEntity> images = new ArrayList<>();
    private List<CommentEntity> comments = new ArrayList<>();
}
