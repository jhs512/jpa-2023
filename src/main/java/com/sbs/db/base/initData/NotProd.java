package com.sbs.db.base.initData;

import com.sbs.db.domain.member.entity.Member;
import com.sbs.db.domain.member.service.MemberService;
import com.sbs.db.domain.question.entity.Answer;
import com.sbs.db.domain.question.entity.Question;
import com.sbs.db.domain.question.service.AnswerService;
import com.sbs.db.domain.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
@Profile("!prod")
public class NotProd {
    @Autowired
    @Lazy
    private NotProd self;
    private final MemberService memberService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    @Bean
    public ApplicationRunner initData() {
        return args -> {
            self.work1();
            self.work2();
        };
    }

    @Transactional
    public void work1() {
        Member member1 = memberService.join("user1", "1234");
        Member member2 = memberService.join("user2", "1234");

        Question question1 = questionService.write(member1, "제목1", "내용1");
        Question question2 = questionService.write(member1, "제목2", "내용2");

        Question question3 = questionService.write(member2, "제목3", "내용3");
        Question question4 = questionService.write(member2, "제목4", "내용4");

        Answer answer1 = answerService.write(member2, question1, "맞아요.");
        Answer answer2 = answerService.write(member2, question2, "그런거 같아요.");
        Answer answer3 = answerService.write(member1, question3, "ㅋㅋㅋ");

        questionService.remove(question3);
        questionService.remove(question4);
    }

    @Transactional
    public void work2() {
        Question question1 = questionService.findById(1L).get();
        Question question2 = questionService.findById(2L).get();
    }
}
