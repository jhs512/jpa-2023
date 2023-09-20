package com.sbs.db.domain.question.entity;

import com.sbs.db.base.jpa.BaseEntity;
import com.sbs.db.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.LinkedList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Question extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    private Member author;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "question", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    @OrderBy("id DESC")
    private List<Answer> answers = new LinkedList<>();
    @ManyToOne(fetch = LAZY)
    private Board board;

    public Answer writeAnswer(Member author, String content) {
        Answer answer = Answer
                .builder()
                .author(author)
                .question(this)
                .content(content)
                .build();

        answers.add(0, answer);

        return answer;
    }

    public void removeAnswer(Answer answer) {
        answers.remove(answer);
    }
}

