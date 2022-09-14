package com.azazafizer.server_v1.api.member.controller;

import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.azazafizer.server_v1.api.member.service.MemberService;
import com.azazafizer.server_v1.common.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                "해당 아이디의 유저 조회 성공",
                member
        );
    }
}
