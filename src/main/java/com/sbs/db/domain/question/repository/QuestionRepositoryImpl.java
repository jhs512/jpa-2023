package com.sbs.db.domain.question.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sbs.db.domain.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.sbs.db.domain.question.entity.QAnswer.answer;
import static com.sbs.db.domain.question.entity.QQuestion.question;

@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public Page<Question> findByKwV3(String kw, Pageable pageable) {
        // Fetching the content (paginated results)
        List<Question> questions = findByKwV3SelectFromJoinWhere(kw)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // Fetching the total count
        long total = findByKwV3SelectFromJoinWhere(kw)
                .fetchCount();

        return new PageImpl<>(questions, pageable, total);
    }

    private JPAQuery<Question> findByKwV3SelectFromJoinWhere(String kw) {
        return jpaQueryFactory
                .selectDistinct(question)
                .from(question)
                .leftJoin(question.author)
                .leftJoin(question.answers, answer)
                .leftJoin(answer.author)
                .where(
                        question.subject.contains(kw)
                                .or(question.content.contains(kw))
                                .or(question.author.username.contains(kw))
                                .or(answer.content.contains(kw))
                                .or(answer.author.username.contains(kw))
                );
    }
}