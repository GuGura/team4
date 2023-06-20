package com.team4.backend.config.jwt;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.team4.backend.config.auth.PrincipalDetails;
import com.team4.backend.model.Token;
import com.team4.backend.model.User;
import com.team4.backend.model.dto.ResultDtoProperties;
import com.team4.backend.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtService jwtService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtService jwtService) {
        super(authenticationManager);
        this.jwtService = jwtService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("CHECK JWT : JwtAuthorizationFilter.doFilterInternal");
        System.out.println("1. 권한이나 인증이 필요한 요청이 전달됨");
        try {
            String tokenType = jwtService.findTokenType(request);
            String jwtToken = jwtService.getTokenFromHeader(request, tokenType);
            User user;
            String email = null;

            System.out.printf("2. %s 확인\n", tokenType);
            jwtToken = jwtToken.replace(JwtProperties.TOKEN_PREFIX, "");

            System.out.println("3. 서명 확인");
            if (tokenType.equals(JwtProperties.HEADER_ACCESS)) {
                DecodedJWT accessToken = jwtService.decodedJWT(jwtToken);
                email = jwtService.getClaim(accessToken, "email", String.class);
            } else if (tokenType.equals(JwtProperties.HEADER_REFRESH)) {
                try {
                    DecodedJWT refreshToken = jwtService.decodedJWT(jwtToken);
                    System.out.println("refreshToken 서명이 정상적으로 진행됨");
                    email = jwtService.getClaim(refreshToken, "email", String.class);

                    user = jwtService.findUserByEmail(email);
                    Map<String, Object> accessJwtMap = jwtService.createAccessToken(user.getId(), user.getEmail(), user.getRole());
                    Map<String, Object> refreshJwtMap = jwtService.createRefreshToken(user.getEmail(), user.getRole());

                    Token token = jwtService.createTokenDto(user.getEmail(), accessJwtMap, refreshJwtMap);
                    jwtService.updateToken(token);
                    response.addHeader(JwtProperties.HEADER_ACCESS, JwtProperties.TOKEN_PREFIX + token.getAccessToken());
                    response.addHeader(JwtProperties.HEADER_REFRESH, JwtProperties.TOKEN_PREFIX + token.getRefreshToken());
                } catch (TokenExpiredException e) {
                    setFailRequest(request, "refreshTokenExpired");
                }
            }
            user = jwtService.findUserByEmail(email);
            PrincipalDetails principalDetails = new PrincipalDetails(user);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.setAttribute(ResultDtoProperties.STATUS, true);
            request.setAttribute(ResultDtoProperties.MESSAGE, "SUCCESS");
        } catch (TokenExpiredException e) {
            setFailRequest(request, "TokenExpired");
        } catch (Exception e) {
            setFailRequest(request, e.getMessage());
        }

        chain.doFilter(request, response);
    }

    private void setFailRequest(HttpServletRequest request, String message) throws IOException {
        System.out.println("setFailResponse: " + message);
        request.setAttribute(ResultDtoProperties.STATUS, false);
        request.setAttribute(ResultDtoProperties.MESSAGE, message);
    }

}
