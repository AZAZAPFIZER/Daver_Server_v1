package com.azazafizer.server_v1.token.domain.ro;

import com.azazafizer.server_v1.token.domain.dto.HttpResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyTokenRo extends HttpResponseDto {

    private VerifyToken data;

    @Getter
    public static class VerifyToken{
        private String memberId;
        private int accessLevel;
        private int apiKeyAccessLevel;
        private int iat;
        private int exp;
        private String iss;
        private String sub;
    }

    public VerifyTokenRo(int status, String message, VerifyToken data){
        super(status,message);
        this.data = data;
    }
}