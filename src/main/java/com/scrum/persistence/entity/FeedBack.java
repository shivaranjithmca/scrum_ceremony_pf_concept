package com.scrum.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "feed_back_t")
public class FeedBack {
    @Id
    @Column(name = "`id`", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "assigned")
    private Long id;
    @Basic
    @Column(name = "`retrospective_id`")
    private Long retrospectiveId;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "`retrospective_id`", referencedColumnName = "`id`", insertable = false, updatable = false)
    private Retrospective retrospective;
    @OneToMany(mappedBy = "feedBack")
    private List<Items> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
