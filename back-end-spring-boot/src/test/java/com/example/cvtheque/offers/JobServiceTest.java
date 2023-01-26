package com.example.cvtheque.offers;

import com.example.cvtheque.comments.CommentEntity;
import com.example.cvtheque.comments.CommentRepository;
import com.example.cvtheque.files.FileEntity;
import com.example.cvtheque.files.FileRepository;
import com.example.cvtheque.users.UserEntity;
import com.example.cvtheque.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith({MockitoExtension.class})
class JobServiceTest {

    @InjectMocks
    private OfferService jobService;
    @InjectMocks
    private OfferDto jobDto;
    @MockBean
    private OfferRepository jobRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private FileRepository imageRepository;

    @BeforeEach
    void setup() {
        this.jobService = new OfferService();
        // object users
        List<UserEntity> users = this.createUserObject();
        Mockito.doReturn(users.get(0)).when(userRepository).findByEmail(Mockito.anyString());
        Mockito.doReturn(users.get(0)).when(userRepository).save(Mockito.any());
        // object jobs
        List<OfferEntity> jobs = this.createJobObject();
        Mockito.doReturn(jobs).when(jobRepository).findByCommentsUserEmail(Mockito.anyString());
        Mockito.doReturn(jobs).when(jobRepository).findByUserEmail(Mockito.anyString());
        Mockito.doReturn(Optional.of(jobs.get(0))).when(jobRepository).findById(Mockito.anyLong());
        Mockito.doReturn(jobs.get(0)).when(jobRepository).save(Mockito.any());
        Mockito.doReturn(jobs).when(jobRepository).findAll();
        // object comments
        List<CommentEntity> comments = this.createCommentbObject();
        Mockito.doReturn(comments.get(0)).when(commentRepository).save(Mockito.any());
        // object images
        List<FileEntity> images = this.createImagesbObject();
        Mockito.doReturn(images.get(0)).when(imageRepository).save(Mockito.any());
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"idriss@gmail.com", "ali@gmail.com"})
    @DisplayName(value = "testing to get jobs by comment user email")
    void getJobByUser(String email) {
        List<OfferEntity> jobs = new ArrayList<>();
        UserEntity user = userRepository.findByEmail(email);
        if(email.equals(user.getEmail())) {
            if(user.getRole().equals("user")) {
                System.out.println("----- test with role user -----");
                jobs = jobRepository.findByCommentsUserEmail(email);
            }else {
                System.out.println("----- test with role client -----");
                jobs = jobRepository.findByUserEmail(email);
            }
            List<OfferDto> jobsDto = jobDto.entityToDtoList(jobs);
            System.out.println(jobsDto.get(0).getTitle());
            assertNotNull(jobsDto);
        }else {
            assertNotNull(null);
        }
    }

    @Test
    @DisplayName(value = "testing to add comment to a job")
    void addCommentToJob() {
            String email = "idriss@gmail.com";
            Long idJob = 1L;
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            UserEntity user = userRepository.findByEmail(email);
            if(email.equals(user.getEmail())) {
                CommentEntity comment = this.createCommentbObject().get(0);
                Optional<OfferEntity> job = jobRepository.findById(idJob);
                comment.setJob(job.get());
                comment.setUser(user);
                comment.setCreated_at(dateFormat.format(now));
                CommentEntity commentAdded = commentRepository.save(comment);
                job.get().getComments().add(commentAdded);
                jobRepository.save(job.get());
                user.getComments().add(commentAdded);
                userRepository.save(user);
                System.out.println("-------- test correct ----------");
                System.out.println(commentAdded.getUser().getEmail());
                assertNotNull(commentAdded);
            }else {
                assertNotNull(null);
            }
    }

    @Test
    @DisplayName(value = "testing change job accepted to true")
    void jobAcceted() {
        Long idJob = 1L;
        Optional<OfferEntity> job = jobRepository.findById(idJob);
        OfferEntity jobRes = null;
        if(job.get().getId().equals(idJob)) {
            job.get().setAccepted(true);
            jobRes = jobRepository.save(job.get());
            System.out.println("--------- current value accepted ----------");
            System.out.println(jobRes.getAccepted());
        }
        assertNotNull(jobRes);
    }

    @Test
    @DisplayName(value = "testing get all jobs")
    void getAllJobs() {
        List<OfferEntity> jobs = jobRepository.findAll();
        List<OfferDto> jobsDto = jobDto.entityToDtoList(jobs);
        assertNotNull(jobsDto);
    }

    @Test
    @DisplayName(value = "testing add job")
    void addJob() {
        String email = "ali@gmail.com";
        OfferEntity job = this.createJobObject().get(0);
        email = job.getUser().getEmail();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        UserEntity user = userRepository.findByEmail(email);
        if(user.getEmail().equals(email)) {
            FileEntity imageEntity = new FileEntity();
            List<FileEntity> listImages = new ArrayList<>();
            for(FileEntity image: job.getImages()) {
                imageEntity.setSrc(image.getSrc());
                listImages.add(imageEntity);
            }
            job.setImages(listImages);
            job.setUser(user);
            FileEntity image = new FileEntity();
            userRepository.save(user);
            job.setCreated_at(dateFormat.format(now));
            OfferEntity jobEntity1 = jobRepository.save(job);
            user.getJobs().add(jobEntity1);
            for(FileEntity img: listImages) {
                image.setJob(jobEntity1);
                image.setSrc(img.getSrc());
                imageRepository.save(image);
            }
            assertNotNull(jobEntity1);
        }else {
            assertNotNull(null);
        }
    }

    // method to create user entity object
    public List<UserEntity> createUserObject() {
        List<UserEntity> users = new ArrayList<>();
        UserEntity user = new UserEntity();
        user.setEmail("idriss@gmail.com");
        user.setFirstName("idriss");
        user.setLastName("ait haddou");
        user.setRole("client");
        users.add(user);
        return users;
    }

    // method to create job entity object
    public List<OfferEntity> createJobObject() {
        List<OfferEntity> jobs = new ArrayList<>();
        OfferEntity job = new OfferEntity();
        job.setId(1L);
        job.setTitle("developer java");
        job.setUser(this.createUserObject().get(0));
        job.getComments().add(this.createCommentbObject().get(0));
        job.setImages(this.createImagesbObject());
        jobs.add(job);
        return jobs;
    }

    // method to create comment entity object
    public List<CommentEntity> createCommentbObject() {
        List<CommentEntity> comments = new ArrayList<>();
        CommentEntity comment = new CommentEntity();
        comment.setComment("first comment");
        comment.setUser(this.createUserObject().get(0));
        comments.add(comment);
        return comments;
    }

    // method to create comment entity object
    public List<FileEntity> createImagesbObject() {
        List<FileEntity> images = new ArrayList<>();
        FileEntity image = new FileEntity();
        image.setId(1L);
        image.setSrc("https://images/1");
        images.add(image);
        return images;
    }

}