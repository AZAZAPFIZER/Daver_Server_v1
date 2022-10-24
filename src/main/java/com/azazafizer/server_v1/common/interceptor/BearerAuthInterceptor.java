package com.azazafizer.server_v1.common.interceptor;

import com.azazafizer.server_v1.api.member.domain.entity.Member;
import com.azazafizer.server_v1.common.annotation.AuthorizationCheck;
import com.azazafizer.server_v1.common.extractor.AuthorizationExtractor;
import com.azazafizer.server_v1.api.token.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
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

        if(!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AuthorizationCheck userLoginToken = handlerMethod.getMethodAnnotation(AuthorizationCheck.class);

        if (userLoginToken == null) {
            return true;
        }

        String token = authExtractor.extract(request, "Bearer");
        if (token == null || token.length() == 0) {
            return true;
        }

        if (tokenService.verifyToken(token) == null) {
            throw new IllegalArgumentException("유효하지 않은 토큰");
        }

        Member member = tokenService.verifyToken(token);
        request.setAttribute("member", member);
        return true;
    }
}