package com.azazafizer.server_v1.api.token.service;

import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.azazafizer.server_v1.api.token.domain.enums.JwtAuth;

public interface TokenService {

    String generateToken(int memberId, JwtAuth jwtAuth);

    Member verifyToken(String token);

    String remakeAccessToken(String token);
}
