package com.sbs.db.domain.question.repository;

import com.sbs.db.domain.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByOrderByIdDesc();

    Page<Question> findDistinctBySubjectContainsOrContentContainsOrAuthor_usernameContainsOrAnswers_contentContainsOrAnswers_author_usernameContains(String kw, String kw1, String kw2, String kw3, String kw4, Pageable pageable);
}
