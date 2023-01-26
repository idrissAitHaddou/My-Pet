package com.example.cvtheque.offers;

import com.example.cvtheque.comments.CommentEntity;
import com.example.cvtheque.requests.OfferRequest;
import com.example.cvtheque.responces.OfferResponce;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api/offer")
@MultipartConfig
public class OfferController {
    @Autowired
    private OfferService jobService;

    @PostMapping(value = "/add")
    public ResponseEntity<Boolean> addJob(@RequestBody @Valid OfferRequest offerRequest) {
        OfferDto offerDto = new OfferDto();
        BeanUtils.copyProperties(offerRequest, offerDto);
        return ResponseEntity.ok(jobService.addJob(offerDto));
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<OfferResponce>> getAllJobs() {
        List<OfferDto> offers = jobService.getAllJobs();
        List<OfferResponce> offerResponces = new ArrayList<>();
        OfferResponce offerRes = new OfferResponce();
        for (OfferDto offer: offers) {
            BeanUtils.copyProperties(offer, offerRes);
            offerResponces.add(offerRes);
        }
        return ResponseEntity.ok(offerResponces);
    }

    @GetMapping(value = "/get/by-title")
    public ResponseEntity<List<OfferResponce>> getJobsByTitle(@RequestParam(value = "title") String title) {
        List<OfferDto> offers = jobService.getJobsByTitle(title);
        List<OfferResponce> offerResponces = new ArrayList<>();
        OfferResponce offerRes = new OfferResponce();
        for (OfferDto offer: offers) {
            BeanUtils.copyProperties(offer, offerRes);
            offerResponces.add(offerRes);
        }
        return ResponseEntity.ok(offerResponces);
    }

}
