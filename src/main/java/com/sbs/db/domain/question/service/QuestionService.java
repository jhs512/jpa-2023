package com.sbs.db.domain.question.service;

import com.sbs.db.domain.question.entity.Question;
import com.sbs.db.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {
    @Autowired
    @Lazy
    private QuestionService self;
    private final QuestionRepository questionRepository;

    @Transactional
    public Question write(String subject, String content) {
        Question question = Question
                .builder()
                .subject(subject)
                .content(content)
                .build();

        self.write2(subject, content);

        return questionRepository.save(question);
    }

    @Transactional
    public Question write2(String subject, String content) {
        Question question = Question
                .builder()
                .subject(subject)
                .content(content)
                .build();

        return questionRepository.save(question);
    }

    public Optional<Question> findById(long id) {
        return questionRepository.findById(id);
    }
}
