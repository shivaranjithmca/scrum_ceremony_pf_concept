package com.scrum.persistence.dao;

import com.scrum.persistence.entity.Retrospective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RetrospectiveDao extends JpaRepository<Retrospective, Integer> {
    @Query("from Retrospective rp where rp.name=:name")
    Optional<Retrospective> findByName(@Param("name") String retrospectiveName);
    @Query("from Retrospective rp where rp.date=:date")
    List<Retrospective> findByDate(@Param("date") String retrospectiveDate);
}
