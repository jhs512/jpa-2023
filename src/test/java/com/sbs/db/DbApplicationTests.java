package com.sbs.db;

import com.sbs.db.domain.question.entity.Question;
import com.sbs.db.domain.question.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class DbApplicationTests {
	@Autowired
	private QuestionService questionService;

	@Test
	@Transactional
	void t1() {
		List<Question> questions = questionService.findAll();

		for (Question question : questions) {
			String username = question.getAuthor().getUsername();
			int size = question.getAnswers().size();
		}
	}
}
