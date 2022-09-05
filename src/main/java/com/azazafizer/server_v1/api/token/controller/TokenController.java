package com.azazafizer.server_v1.api.token.controller;

import com.azazafizer.server_v1.api.token.domain.dto.RemakeAccessTokenDto;
import com.azazafizer.server_v1.api.token.service.TokenService;
import com.azazafizer.server_v1.common.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/refresh")
    public ResponseData<String> remakeAccessToken(
            @RequestBody RemakeAccessTokenDto dto
    ) {
        String token = tokenService.remakeAccessToken(dto.getToken());
        return new ResponseData<>(
                HttpStatus.OK,
                "토큰 재발급 성공",
                token
        );
    }
}
