package com.scrum.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "`feed_back_item_t`")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "`feed_back_id`")
    private Long feedBackId;
    @Basic
    @Column(name = "`type_id`")
    private Long typeId;
    @Basic
    @Column(name = "`name`", length = 100)
    private String name;
    @Basic
    @Column(name = "`body`", length = 200)
    private String body;
    @ManyToOne
    @JoinColumn(name = "`type_id`", referencedColumnName = "`id`", insertable = false, updatable = false)
    private FeedBackType feedBackType;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "`feed_back_id`", referencedColumnName = "`id`", insertable = false, updatable = false)
    private FeedBack feedBack;
    @Transient
    private String feedbackType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(Long feedBackId) {
        this.feedBackId = feedBackId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public FeedBackType getFeedBackType() {
        return feedBackType;
    }

    public void setFeedBackType(FeedBackType feedBackType) {
        this.feedBackType = feedBackType;
    }

    public FeedBack getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(FeedBack feedBack) {
        this.feedBack = feedBack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
