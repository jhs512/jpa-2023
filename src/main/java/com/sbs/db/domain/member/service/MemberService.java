package com.sbs.db.domain.member.service;

import com.sbs.db.domain.member.entity.Member;
import com.sbs.db.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member join(String username, String password) {
        Member member = Member
                .builder()
                .username(username)
                .password(password)
                .build();

        return memberRepository.save(member);
    }

    public Optional<Member> findById(long id) {
        return memberRepository.findById(id);
    }
}
