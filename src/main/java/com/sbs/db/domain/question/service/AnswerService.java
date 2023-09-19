package com.sbs.db.domain.question.service;

import com.sbs.db.domain.member.entity.Member;
import com.sbs.db.domain.question.entity.Answer;
import com.sbs.db.domain.question.entity.Question;
import com.sbs.db.domain.question.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {
    private final AnswerRepository answerRepository;

    @Transactional
    public Answer write(Member author, Question question, String content) {
        Answer answer = Answer
                .builder()
                .author(author)
                .question(question)
                .content(content)
                .build();

        return answerRepository.save(answer);
    }

    public Optional<Answer> findById(long id) {
        return answerRepository.findById(id);
    }

    public List<Answer> findByQuestion(Question question) {
        return answerRepository.findByQuestion(question);
    }

    public void remove(Answer answer) {
        answerRepository.delete(answer);
    }
}
