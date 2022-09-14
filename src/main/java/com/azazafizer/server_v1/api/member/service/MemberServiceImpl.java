package com.azazafizer.server_v1.api.member.service;

import com.azazafizer.server_v1.api.member.domain.dto.JoinDto;
import com.azazafizer.server_v1.api.member.domain.dto.LoginDto;
import com.azazafizer.server_v1.api.member.domain.dto.ModifyMemberDto;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.azazafizer.server_v1.api.member.domain.repository.MemberRepository;
import com.azazafizer.server_v1.api.member.domain.ro.LoginRo;
import com.azazafizer.server_v1.api.token.domain.enums.JwtAuth;
import com.azazafizer.server_v1.api.token.service.TokenService;
import com.azazafizer.server_v1.common.Encrypt;
import com.azazafizer.server_v1.api.token.controller.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final TokenService tokenService;
    private final MemberRepository memberRepository;
    private final Encrypt encrypt;


    @Override
    public Member getMemberById(int id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 회원은 존재하지 않습니다"));
    }

    @Override
    public void join(JoinDto joinDto) {
        String pw = encrypt.sha512(joinDto.getPw());

        Member member = Member.builder()
                .pw(pw)
                .name(joinDto.getName())
                .email(joinDto.getEmail())
                .build();
        memberRepository.save(member);
    }

    @Override
    public LoginRo login(LoginDto loginDto) {
        String pw = encrypt.sha512(loginDto.getPw());

        Member member = memberRepository.findByEmailAndPw(loginDto.getEmail(), pw)
                .orElseThrow(() -> new NotFoundException("해당 회원은 존재하지 않습니다"));
        String accessToken = tokenService.generateToken(member.getId(), JwtAuth.ACCESS);
        String refreshToken = tokenService.generateToken(member.getId(), JwtAuth.REFRESH);
        return new LoginRo(member, accessToken, refreshToken);
    }

    @Override
    public void modifyMemberInfo(Member member, ModifyMemberDto dto) {
        member.updateMemberInfo(
                dto.getName() != null ? member.getName() : dto.getName(),
                dto.getEmail() != null ? member.getEmail() : dto.getEmail(),
                dto.getProfileImage());
        memberRepository.save(member);
    }
}
