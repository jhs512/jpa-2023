package com.sbs.db.domain.question.entity;

import com.sbs.db.base.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Question extends BaseEntity {
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;
}
