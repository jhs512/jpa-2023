package com.sbs.db.domain.question.repository;

import com.sbs.db.domain.question.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
