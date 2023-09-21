package com.sbs.db.domain.question.repository;

import com.sbs.db.domain.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByOrderByIdDesc();

    Page<Question> findDistinctBySubjectContainsOrContentContainsOrAuthor_usernameContainsOrAnswers_contentContainsOrAnswers_author_usernameContains(String kw, String kw1, String kw2, String kw3, String kw4, Pageable pageable);

    @Query("""
            SELECT DISTINCT q
            FROM Question q
            LEFT OUTER JOIN Member qm
            ON q.author = qm
            LEFT OUTER JOIN Answer a
            ON a.question = q
            LEFT OUTER JOIN Member am
            ON a.author = am
            WHERE q.subject LIKE %:kw%
            OR q.content LIKE %:kw%
            OR qm.username LIKE %:kw%
            OR a.content LIKE %:kw%
            OR am.username LIKE %:kw%
            """)
    Page<Question> findByKwV2(@Param("kw") String kw, Pageable pageable);
}
