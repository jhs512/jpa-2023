package com.sbs.db.domain.question.service;

import com.sbs.db.domain.question.entity.Board;
import com.sbs.db.domain.question.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Board create(String name) {
        Board question = Board
                .builder()
                .name(name)
                .build();

        return boardRepository.save(question);
    }
}
