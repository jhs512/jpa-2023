package com.sbs.db.domain.question.entity;

import com.sbs.db.base.jpa.BaseEntity;
import com.sbs.db.domain.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
public class Answer extends BaseEntity {
    @ManyToOne
    private Member author;
    @ManyToOne
    private Question question;
    @Column(columnDefinition = "TEXT")
    private String content;
}
