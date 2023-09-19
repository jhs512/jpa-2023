package com.sbs.db.domain.question.service;

import com.sbs.db.domain.member.entity.Member;
import com.sbs.db.domain.question.entity.Question;
import com.sbs.db.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerService answerService;

    @Transactional
    public Question write(Member author, String subject, String content) {
        Question question = Question
                .builder()
                .author(author)
                .subject(subject)
                .content(content)
                .build();

        return questionRepository.save(question);
    }

    public Optional<Question> findById(long id) {
        return questionRepository.findById(id);
    }

    public void remove(Question question) {
        answerService.findByQuestion(question).forEach(answer -> {
            answerService.remove(answer);
        });

        questionRepository.delete(question);
    }
}
