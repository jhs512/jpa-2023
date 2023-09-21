package com.sbs.db.domain.question.service;

import com.sbs.db.domain.member.entity.Member;
import com.sbs.db.domain.question.entity.Question;
import com.sbs.db.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {
    private final QuestionRepository questionRepository;

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
        questionRepository.delete(question);
    }

    public List<Question> findAll() {
        return questionRepository.findByOrderByIdDesc();
    }

    public Page<Question> search(String kw, int page, String sortCode) {
        kw = kw.trim();
        List<Sort.Order> sorts = new ArrayList<>();

        switch (sortCode) {
            case "OLD" -> sorts.add(Sort.Order.asc("id")); // 오래된순
            default -> sorts.add(Sort.Order.desc("id")); // 최신순
        }

        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(sorts)); // 한 페이지에 10까지 가능

        if (kw == null || kw.length() == 0) {
            return questionRepository.findAll(pageable);
        }

        // Distinct : 중복 제거
        // SubjectContains : 제목
        // ContentContains : 내용
        // Author_usernameContains : 작성자 아이디
        // Answers_contentContains : 답변 내용
        // Answers_author_username : 답변 작성자 아이디
        return questionRepository.findDistinctBySubjectContainsOrContentContainsOrAuthor_usernameContainsOrAnswers_contentContainsOrAnswers_author_usernameContains(kw, kw, kw, kw, kw, pageable);
    }

    public Page<Question> searchV2(String kw, int page, String sortCode) {
        kw = kw.trim();
        List<Sort.Order> sorts = new ArrayList<>();

        switch (sortCode) {
            case "OLD" -> sorts.add(Sort.Order.asc("id")); // 오래된순
            default -> sorts.add(Sort.Order.desc("id")); // 최신순
        }

        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); // 한 페이지에 10까지 가능

        if (kw == null || kw.length() == 0) {
            return questionRepository.findAll(pageable);
        }

        return questionRepository.findByKwV2(kw, pageable);
    }
}
