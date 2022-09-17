package com.azazafizer.server_v1.api.member.controller;

import com.azazafizer.server_v1.api.member.domain.dto.JoinDto;
import com.azazafizer.server_v1.api.member.domain.dto.LoginDto;
import com.azazafizer.server_v1.api.member.domain.dto.ModifyMemberDto;
import com.azazafizer.server_v1.api.member.domain.dto.ModifyPwDto;
import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.azazafizer.server_v1.api.member.domain.ro.LoginRo;
import com.azazafizer.server_v1.api.member.service.MemberService;
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
            @PathVariable int id
    ) {
        Member member = memberService.getMemberById(id);
        return new ResponseData<>(
                HttpStatus.OK,
                "해당 아이디의 회원 조회 성공",
                member
        );
    }

    @PostMapping("/join")
    public Response<C> join(@RequestBody @Valid JoinDto joinDto) {
        memberService.join(joinDto);
        return new Response<C>(
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

    @PatchMapping
    public Response<C> modifyMemberInfo(
            @RequestAttribute Member member,
            @RequestBody ModifyMemberDto dto
    ) {
        memberService.modifyMemberInfo(member, dto);
        return new Response<C>(
                HttpStatus.OK,
                "회원 정보 수정 성공"
        );
    }

    @PatchMapping("/pw")
    public Response<C> modifyPw(
            @RequestAttribute Member member,
            @RequestBody @Valid ModifyPwDto dto
    ) {
        memberService.modifyPw(member, dto);
        return new Response<C>(
                HttpStatus.OK,
                "패스원드 수정 성공"
        );
    }
}
