package com.azazafizer.server_v1.api.member.service;

import com.azazafizer.server_v1.api.member.domain.dto.*;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.azazafizer.server_v1.api.member.domain.repository.MemberRepository;
import com.azazafizer.server_v1.api.member.domain.ro.LoginRo;
import com.azazafizer.server_v1.api.token.domain.enums.JwtAuth;
import com.azazafizer.server_v1.api.token.service.TokenService;
import com.azazafizer.server_v1.common.Encrypt;
import com.azazafizer.server_v1.common.exception.BadRequestException;
import com.azazafizer.server_v1.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final TokenService tokenService;
    private final MemberRepository memberRepository;
    private final Encrypt encrypt;


    @Override
    public Member getMemberById(String id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 회원은 존재하지 않습니다"));
    }

    @Override
    public void join(JoinDto joinDto) {
        String pw = encrypt.sha512(joinDto.getPw());

        if (memberRepository.existsById(joinDto.getId())) {
            throw new BadRequestException("이미 존재하는 아이디 입니다");
        }
        Member member = Member.builder()
                .id(joinDto.getId())
                .pw(pw)
                .name(joinDto.getName())
                .build();
        memberRepository.save(member);
    }

    @Override
    public LoginRo login(LoginDto loginDto) {
        String pw = encrypt.sha512(loginDto.getPw());

        Member member = memberRepository.findByIdAndPw(loginDto.getId(), pw)
                .orElseThrow(() -> new NotFoundException("해당 회원은 존재하지 않습니다"));
        String accessToken = tokenService.generateToken(member.getId(), JwtAuth.ACCESS);
        String refreshToken = tokenService.generateToken(member.getId(), JwtAuth.REFRESH);
        return new LoginRo(member, accessToken, refreshToken);
    }

    @Override
    public void modifyMemberInfo(Member member, ModifyMemberDto dto) {
        member.updateMemberInfo(
                dto.getName() != null ? member.getName() : dto.getName(),
                dto.getProfileImage());
        memberRepository.save(member);
    }

    @Override
    public void modifyPw(Member member, ModifyPwDto dto) {
        String pw = encrypt.sha512(dto.getPw());

        member.updatePw(pw);
        memberRepository.save(member);
    }

    @Override
    public void authorizationPw(GetPwDto dto) {
        memberRepository.findByIdAndName(dto.getId(), dto.getName())
                .orElseThrow(() -> new NotFoundException("해당 회원은 존재하지 않습니다"));
    }
}
