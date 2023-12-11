package com.scrum.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "`feed_back_type_t`")
public class FeedBackType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "`type`", nullable = false, length = 50)
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
