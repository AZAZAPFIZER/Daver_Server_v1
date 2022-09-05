package com.azazafizer.server_v1.token.service;

import com.azazafizer.server_v1.token.domain.enums.JwtAuth;

public interface TokenService {

    String generateToken(String memberId, JwtAuth jwtAuth);

    String verifyToken(String token);

    String remakeAccessToken(String token);
}
