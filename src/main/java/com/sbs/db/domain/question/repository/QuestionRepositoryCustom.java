package com.sbs.db.domain.question.repository;

import com.sbs.db.domain.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionRepositoryCustom {
    Page<Question> findByKwV3(String kw, Pageable pageable);

    Page<Question> findByKwV4(List<String> optionCodes, String kw, Pageable pageable);
}
