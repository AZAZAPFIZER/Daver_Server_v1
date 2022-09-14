package com.azazafizer.server_v1.api.token.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpResponseDto {

    private int status;
    private String message;
}
