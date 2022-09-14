package com.azazafizer.server_v1.api.member.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDto {

    private String email;
    private String pw;
}
