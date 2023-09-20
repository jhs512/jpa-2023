package com.sbs.db;

import com.sbs.db.domain.question.entity.Question;
import com.sbs.db.domain.question.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class DbApplicationTests {
	@Autowired
	private QuestionService questionService;

	@Test
	@Transactional
	void t1() {
		Question question1 = questionService.findById(1L).get();
		System.out.println(question1.getAnswers());
	}
}
