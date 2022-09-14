package com.azazafizer.server_v1.api.member.domain.ro;

import com.azazafizer.server_v1.api.member.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRo {
    private Member member;
    private String accessToken;
    private String refreshToken;
}
