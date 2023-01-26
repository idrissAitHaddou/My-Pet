package com.example.cvtheque.offers;

import com.example.cvtheque.comments.CommentDto;
import com.example.cvtheque.comments.CommentEntity;
import com.example.cvtheque.comments.CommentRepository;
import com.example.cvtheque.exception.ApiException;
import com.example.cvtheque.files.FileEntity;
import com.example.cvtheque.files.FileRepository;
import com.example.cvtheque.users.UserEntity;
import com.example.cvtheque.users.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private FileRepository imgRepository;
    @Autowired
    private OfferDto jobDto;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    public Boolean addCommentToJob(String email, Long idJob, CommentDto commentDto) {
        try {
            CommentEntity comment = new CommentEntity();
            BeanUtils.copyProperties(commentDto, comment);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            UserEntity user = userRepository.findByEmail(email);
            Optional<OfferEntity> job = offerRepository.findById(idJob);
            comment.setJob(job.get());
            comment.setUser(user);
            comment.setCreated_at(dateFormat.format(now));
            CommentEntity commentAdded = commentRepository.save(comment);
            job.get().getComments().add(commentAdded);
            offerRepository.save(job.get());
            user.getComments().add(commentAdded);
            userRepository.save(user);
            return true;
        }catch (Exception e) {
            throw new ApiException("not created", HttpStatus.CREATED);
        }
    }

    public List<OfferDto> getAllJobs() {
        List<OfferEntity> offers = offerRepository.findAllByOrderByIdDesc();
        List<OfferDto> jobsDto = jobDto.entityToDtoList(offers);
        return jobsDto;
    }

    public List<OfferDto> getJobsByTitle(String title) {
        List<OfferEntity> jobs = offerRepository.findByTitle(title);
        List<OfferDto> jobsDto = jobDto.entityToDtoList(jobs);
        return jobsDto;
    }

    public Boolean addJob(OfferDto offerDto) {
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            UserEntity user = userRepository.findByEmail(offerDto.getEmailCurrentuser());
            FileEntity imageEntity = new FileEntity();
            List<FileEntity> listImages = new ArrayList<>();
            for(String image: offerDto.getImagesBase()) {
                imageEntity.setSrc(image);
                    listImages.add(imageEntity);
                imageEntity = null;
            }
            OfferEntity offerEntity = new OfferEntity();
            BeanUtils.copyProperties(offerDto, offerEntity);
            offerEntity.setImages(listImages);
            offerEntity.setUser(user);
            FileEntity image = new FileEntity();
            userRepository.save(user);
            offerEntity.setCreated_at(dateFormat.format(now));
            OfferEntity offerEntityCreated = offerRepository.save(offerEntity);
            user.getJobs().add(offerEntityCreated);
            for(FileEntity img: listImages) {
                image.setJob(offerEntityCreated);
                image.setSrc(img.getSrc());
                imgRepository.save(image);
            }
            return true;
        }catch (Exception e) {
            throw new ApiException("offer not created", HttpStatus.CREATED);
        }
    }

}
