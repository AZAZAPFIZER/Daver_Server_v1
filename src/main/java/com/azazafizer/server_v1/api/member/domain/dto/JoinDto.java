package com.azazafizer.server_v1.api.member.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class JoinDto {

    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String pw;
    @NotEmpty
    private String residence;
}
