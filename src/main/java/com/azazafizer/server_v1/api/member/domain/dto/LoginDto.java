package com.azazafizer.server_v1.api.member.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class LoginDto {

    @NotEmpty
    private String email;
    @NotEmpty
    private String pw;
}
