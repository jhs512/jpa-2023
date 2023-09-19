package com.sbs.db.base.initData;

import com.sbs.db.domain.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
@Profile("!prod")
public class NotProd {
    @Bean
    public ApplicationRunner initData(QuestionService questionService) {
        return args -> {
            questionService.write("제목1", "내용1");
        };
    }
}
