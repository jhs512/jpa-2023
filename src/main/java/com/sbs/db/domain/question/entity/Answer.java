package com.sbs.db.domain.question.entity;

import com.sbs.db.base.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Answer extends BaseEntity {
    @ManyToOne
    private Question question;
    @Column(columnDefinition = "TEXT")
    private String content;
}
