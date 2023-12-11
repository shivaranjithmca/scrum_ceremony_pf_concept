package com.scrum.persistence.dao;

import com.scrum.persistence.entity.FeedBackType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedBackTypeDao extends JpaRepository<FeedBackType, Integer> {
    @Query("from FeedBackType f where f.type=:type")
    Optional<FeedBackType> findByType(@Param("type") String type);
}
