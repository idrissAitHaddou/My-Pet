package com.example.cvtheque.comments;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
//    @Query("SELECT j FROM JobEntity j where j.user.email = :email")
//    List<JobEntity> findByJobId(Long id);
}
