package com.sbs.db.domain.question.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sbs.db.domain.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.sbs.db.domain.question.entity.QQuestion.question;

@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {
    private final JPAQueryFactory jpqQueryFactory;

    public Page<Question> findByKwV3(String kw, Pageable pageable) {
        List<Question> questions = jpqQueryFactory
                .selectDistinct(question)
                .from(question)
                .where(
                        question.subject.contains(kw)
                                .or(question.content.contains(kw))
                )
                .fetch();

        return new PageImpl<>(questions, pageable, questions.size());
    }
}
