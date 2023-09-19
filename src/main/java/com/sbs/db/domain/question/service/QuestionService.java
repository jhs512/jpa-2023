package com.sbs.db.domain.question.service;

import com.sbs.db.domain.question.entity.Question;
import com.sbs.db.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    @Autowired
    @Lazy
    private QuestionService self;
    private final QuestionRepository questionRepository;

    public Question write(String subject, String content) {
        return self.write2(subject, content);
    }

    @Transactional
    public Question write2(String subject, String content) {
        Question question = Question
                .builder()
                .subject(subject)
                .content(content)
                .build();

        Question question2 = Question
                .builder()
                .subject(subject)
                .content(content)
                .build();

        questionRepository.save(question2);

        return questionRepository.save(question);
    }
}
