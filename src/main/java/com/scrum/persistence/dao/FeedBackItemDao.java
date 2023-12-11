package com.scrum.persistence.dao;

import com.scrum.persistence.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackItemDao extends JpaRepository<Items, Integer> {
}
