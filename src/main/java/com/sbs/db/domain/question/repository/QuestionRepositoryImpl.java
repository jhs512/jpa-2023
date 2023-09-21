package com.sbs.db.domain.question.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sbs.db.domain.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.sbs.db.domain.question.entity.QAnswer.answer;
import static com.sbs.db.domain.question.entity.QQuestion.question;

@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public Page<Question> findByKwV3(String kw, Pageable pageable) {
        // Fetching the content (paginated results)
        JPAQuery<Question> questionsQuery = findByKwV3SelectFromJoinWhere(kw);

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(question.getType(), question.getMetadata());
            questionsQuery.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(o.getProperty())));
        }

        List<Question> questions = questionsQuery
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

    @Override
    public Page<Question> findByKwV4(List<String> optionCodes, String kw, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        if (optionCodes.contains("subject")) {
            builder.or(question.subject.contains(kw));
        }

        if (optionCodes.contains("content")) {
            builder.or(question.content.contains(kw));
        }

        if (optionCodes.contains("questionAuthorUsername")) {
            builder.or(question.author.username.contains(kw));
        }

        if (optionCodes.contains("answerAuthorUsername")) {
            builder.or(answer.author.username.contains(kw));
        }

        // Fetching the content (paginated results)
        JPAQuery<Question> questionsQuery = findByKwV4SelectFromJoinWhere(builder);

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(question.getType(), question.getMetadata());
            questionsQuery.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(o.getProperty())));
        }

        List<Question> questions = questionsQuery
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // Fetching the total count
        long total = findByKwV4SelectFromJoinWhere(builder)
                .fetchCount();

        return new PageImpl<>(questions, pageable, total);
    }

    private JPAQuery<Question> findByKwV4SelectFromJoinWhere(BooleanBuilder predicate) {
        return jpaQueryFactory
                .selectDistinct(question)
                .from(question)
                .leftJoin(question.author)
                .leftJoin(question.answers, answer)
                .leftJoin(answer.author)
                .where(predicate);
    }
}