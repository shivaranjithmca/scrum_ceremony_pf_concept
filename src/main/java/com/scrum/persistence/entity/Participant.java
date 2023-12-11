package com.scrum.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "`participant_t`")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "`name`", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "`retrospective_id`")
    private Long retrospectiveId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "`retrospective_id`", referencedColumnName = "`id`", insertable = false, updatable = false)
    private Retrospective retrospective;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRetrospectiveId() {
        return retrospectiveId;
    }

    public void setRetrospectiveId(Long retrospectiveId) {
        this.retrospectiveId = retrospectiveId;
    }

    public Retrospective getRetrospective() {
        return retrospective;
    }

    public void setRetrospective(Retrospective retrospective) {
        this.retrospective = retrospective;
    }
}
