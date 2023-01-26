package com.example.cvtheque.offers;

import com.example.cvtheque.comments.CommentEntity;
import com.example.cvtheque.files.FileEntity;
import com.example.cvtheque.users.UserEntity;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class OfferDto {
    private Long id;
    private String title;
    private String campany;
    private String description;
    private String created_at;
    private Boolean accepted = false;
    private UserEntity user;
    private String emailCurrentuser;
    private List<FileEntity> images = new ArrayList<>();
    private List<CommentEntity> comments = new ArrayList<>();
    private List<String> imagesBase = new ArrayList<>();

    public OfferEntity jobDtoToEntity(OfferDto jobDto){
        OfferEntity job = new OfferEntity();
        job.setId(jobDto.getId());
        job.setTitle(jobDto.getTitle());
        job.setCampany(jobDto.getCampany());
        job.setDescription(jobDto.getDescription());
        job.setCreated_at(jobDto.getCreated_at());
        return job;
    }

    public OfferDto jobEntityToDto(OfferEntity jobEntity){
        return OfferDto.builder()
                .id(jobEntity.getId())
                .title(jobEntity.getTitle())
                .campany(jobEntity.getCampany())
                .description(jobEntity.getDescription())
                .created_at(jobEntity.getCreated_at())
                .comments(jobEntity.getComments())
                .images(jobEntity.getImages())
                .user(jobEntity.getUser())
                .build();
    }

    public List<OfferDto> entityToDtoList(List<OfferEntity> jobs) {
        List<OfferDto> jobList = new ArrayList<>();
        jobs.forEach(job -> {
            OfferDto jobDto = OfferDto.builder()
                    .id(job.getId())
                    .title(job.getTitle())
                    .campany(job.getCampany())
                    .description(job.getDescription())
                    .created_at(job.getCreated_at())
                    .comments(job.getComments())
                    .images(job.getImages())
                    .user(job.getUser())
                    .build();
            jobDto.getUser().setPassword("");
            jobList.add(jobDto);
        });
        return jobList;
    }

}
