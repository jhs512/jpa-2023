package com.sbs.db.domain.question.repository;

import com.sbs.db.domain.question.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
