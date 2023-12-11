package com.scrum.persistence.dao;

import com.scrum.persistence.entity.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackDao extends JpaRepository<FeedBack, Integer> {
}
