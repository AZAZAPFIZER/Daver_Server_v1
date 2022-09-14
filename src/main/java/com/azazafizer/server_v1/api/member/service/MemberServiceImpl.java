package com.azazafizer.server_v1.api.member.service;

import com.azazafizer.server_v1.api.member.domain.dto.JoinDto;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.azazafizer.server_v1.api.member.domain.repository.MemberRepository;
import com.azazafizer.server_v1.common.Encrypt;
import com.azazafizer.server_v1.api.token.controller.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final Encrypt encrypt;


    @Override
    public Member getMemberById(String id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 유저는 존재하지 않습니다"));
    }

    @Override
    public void join(JoinDto joinDto) {
        String pw = encrypt.sha512(joinDto.getPw());

        Member member = Member.builder()
                .id(joinDto.getId())
                .pw(pw)
                .name(joinDto.getName())
                .email(joinDto.getEmail())
                .build();
        memberRepository.save(member);
    }
}
