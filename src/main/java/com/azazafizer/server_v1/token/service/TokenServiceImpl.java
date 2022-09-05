package com.azazafizer.server_v1.token.service;

import com.azazafizer.server_v1.common.exception.BadRequestException;
import com.azazafizer.server_v1.common.exception.GoneException;
import com.azazafizer.server_v1.common.exception.InternalServerException;
import com.azazafizer.server_v1.common.properties.AppProperties;
import com.azazafizer.server_v1.token.domain.enums.JwtAuth;
import com.azazafizer.server_v1.token.domain.ro.VerifyTokenRo;
import io.jsonwebtoken.*;
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
        claims.put("memberId", memberId);

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
    public String remakeAccessToken(String refreshToken) {
        if (refreshToken == null || refreshToken.trim().isEmpty()) {
            throw new BadRequestException("토큰이 전송되지 않았습니다");
        }

        Claims claims = this.parseToken(refreshToken, JwtAuth.REFRESH);
//        User user = userServiceImpl.findById(claims.get("userId").toString());

        return generateToken("", JwtAuth.ACCESS);
    }

    @Override
    public String getMemberIdByToken(String token) {
        return null;
    }

    private Claims parseToken(String token, JwtAuth jwtAuth) {
        try {
            return Jwts.parser()
                    .setSigningKey(
                            jwtAuth == JwtAuth.ACCESS
                                    ? appProperties.getSecret()
                                    : appProperties.getRefreshSecret()
                    )
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new GoneException("토큰이 만료되었습니다");
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("토큰이 없습니다");
        } catch (Exception e) {
            throw new InternalServerException("토큰 해석 중 에러 발생");
        }
    }
}
