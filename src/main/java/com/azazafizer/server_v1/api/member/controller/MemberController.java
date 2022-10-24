package com.azazafizer.server_v1.api.member.controller;

import com.azazafizer.server_v1.api.member.domain.dto.*;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.azazafizer.server_v1.api.member.domain.ro.LoginRo;
import com.azazafizer.server_v1.api.member.service.MemberService;
import com.azazafizer.server_v1.common.annotation.AuthorizationCheck;
import com.azazafizer.server_v1.common.response.Response;
import com.azazafizer.server_v1.common.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    public ResponseData<Member> getMemberById(
            @PathVariable String id
    ) {
        Member member = memberService.getMemberById(id);
        return new ResponseData<>(
                HttpStatus.OK,
                "해당 아이디의 회원 조회 성공",
                member
        );
    }

    @PostMapping("/join")
    public Response join(@RequestBody @Valid JoinDto joinDto) {
        memberService.join(joinDto);
        return new Response(
                HttpStatus.CREATED,
                "회원가입 성공"
        );
    }

    @PostMapping("/login")
    public ResponseData<LoginRo> login(@RequestBody @Valid LoginDto loginDto) {
        LoginRo loginData = memberService.login(loginDto);
        return new ResponseData<>(
                HttpStatus.CREATED,
                "로그인 성공",
                loginData
        );
    }

    @AuthorizationCheck
    @PatchMapping
    public Response modifyMemberInfo(
            @RequestAttribute Member member,
            @RequestBody ModifyMemberDto dto
    ) {
        memberService.modifyMemberInfo(member, dto);
        return new Response(
                HttpStatus.OK,
                "회원 정보 수정 성공"
        );
    }

    @AuthorizationCheck
    @PatchMapping("/pw")
    public Response modifyPw(
            @RequestAttribute Member member,
            @RequestBody @Valid ModifyPwDto dto
    ) {
        memberService.modifyPw(member, dto);
        return new Response(
                HttpStatus.OK,
                "패스워드 수정 성공"
        );
    }

    @PostMapping("/pw")
    public Response authorizationPw(
            @RequestBody @Valid GetPwDto dto
    ) {
        memberService.authorizationPw(dto);
        return new Response(
                HttpStatus.OK,
                "패스워드를 찾기 위한 인증 성공"
        );
    }
}
