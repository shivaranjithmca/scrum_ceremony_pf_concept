package com.scrum.persistence.dao;

import com.scrum.persistence.entity.Participant;
import com.scrum.persistence.entity.Retrospective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantDao extends JpaRepository<Participant, Integer> {
    @Query("from Participant p where p.name=:name")
    Optional<Participant> findByName(@Param("name") String participantName);
}
