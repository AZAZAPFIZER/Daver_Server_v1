package com.azazafizer.server_v1.common.interceptor;

import com.b1nd.dodamdodam.common.extractor.AuthorizationExtractor;
import com.b1nd.dodamdodam.token.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor
public class BearerAuthInterceptor implements HandlerInterceptor {
    private AuthorizationExtractor authExtractor;
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        String token = authExtractor.extract(request, "Bearer");
        if (token == null || token.length() == 0) {
            return true;
        }

        if (tokenService.verifyToken(token) == null) {
            throw new IllegalArgumentException("유효하지 않은 토큰");
        }

        String member = tokenService.getMemberIdByToken(token);
        request.setAttribute("member", member);
        return true;
    }
}