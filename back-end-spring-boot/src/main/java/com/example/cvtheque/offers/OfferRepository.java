package com.example.cvtheque.offers;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

    @Query("SELECT f FROM OfferEntity f where f.user.email = :email")
    List<OfferEntity> findByUserEmail(String email);

    @Query("SELECT f FROM OfferEntity f where f.comments.user.email = :email")
    List<OfferEntity> findByCommentsUserEmail(String email);
    List<OfferEntity> findByTitle(String title);
    List<OfferEntity> findAllByOrderByIdDesc();
}
