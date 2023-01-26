package com.example.cvtheque.comments;

import com.example.cvtheque.offers.OfferService;
import com.example.cvtheque.requests.CommentRequest;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/comment")
@MultipartConfig
public class CommentController {
    @Autowired
    private OfferService jobService;

    @PostMapping(value = "/create")
    public ResponseEntity<Boolean> addJob(@RequestBody @Valid CommentRequest commentRequest, @RequestParam(value = "idJob") Long idJob, @RequestParam(value = "email") String email) {
        CommentDto commentDto = new CommentDto();
        BeanUtils.copyProperties(commentRequest, commentDto);
        Boolean status = jobService.addCommentToJob(email,idJob, commentDto);
        return ResponseEntity.ok(status);
    }
}
