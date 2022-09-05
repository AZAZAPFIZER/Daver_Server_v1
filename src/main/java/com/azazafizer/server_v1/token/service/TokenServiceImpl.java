package com.azazafizer.server_v1.token.service;

import com.azazafizer.server_v1.common.properties.AppProperties;
import com.azazafizer.server_v1.token.domain.enums.JwtAuth;
import com.azazafizer.server_v1.token.domain.ro.VerifyTokenRo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{

    private final AppProperties appProperties;
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @Override
    public String generateToken(String memberId, JwtAuth jwtAuth) {
        Date expiredAt = new Date();
        String secretKey;

        if (jwtAuth == JwtAuth.ACCESS) {
            expiredAt = new Date(expiredAt.getTime() + appProperties.getJWT_ACCESS_EXPIRE());
            secretKey = appProperties.getSecret();
        } else {
            expiredAt = new Date(expiredAt.getTime() + appProperties.getJWT_REFRESH_EXPIRE());
            secretKey = appProperties.getRefreshSecret();
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", memberId);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(jwtAuth.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiredAt)
                .signWith(signatureAlgorithm, secretKey)
                .compact();
    }

    @Override
    public VerifyTokenRo verifyToken(String token) {
        return null;
    }

    @Override
    public String generateRefreshToken(String memberId, int accessLevel) {
        return null;
    }

    @Override
    public String remakeAccessToken(String token) {
        return null;
    }

    @Override
    public String getMemberIdByToken(String token) {
        return null;
    }
}
