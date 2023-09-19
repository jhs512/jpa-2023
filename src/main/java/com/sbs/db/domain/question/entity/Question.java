package com.sbs.db.domain.question.entity;

import com.sbs.db.base.jpa.BaseEntity;
import com.sbs.db.domain.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends BaseEntity {
    @ManyToOne
    private Member author;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Builder.Default
    @OneToMany(mappedBy = "question", cascade = {jakarta.persistence.CascadeType.PERSIST})
    private List<Answer> answers = new ArrayList<>();

    public void writeAnswer(Member author, String content) {
        Answer answer = Answer
                .builder()
                .author(author)
                .question(this)
                .content(content)
                .build();

        answers.add(answer);
    }
}

