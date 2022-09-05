package com.azazafizer.server_v1.token.service;

import com.azazafizer.server_v1.token.domain.enums.JwtAuth;
import com.azazafizer.server_v1.token.domain.ro.VerifyTokenRo;

public interface TokenService {

    String generateToken(String memberId, JwtAuth jwtAuth);

    VerifyTokenRo verifyToken(String token);

    String remakeAccessToken(String token);

    String getMemberIdByToken(String token);
}
