package com.example.myappzuulgateway.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    Environment env;

    public AuthorizationFilter(AuthenticationManager authenticationManager, Environment env) {
        super(authenticationManager);
        this.env = env;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        //1.header에 token 포함 여부
        String authorizationHeader = request.getHeader(
                env.getProperty("authorization.token.header.name"));
        //2.token이 없거나, Bearer token이 아니면 오류
        if (authorizationHeader == null || !authorizationHeader.startsWith(env.getProperty("authorization.token.header.prefix"))){
            chain.doFilter(request, response);
            return;
        }
        //3. UsernamePasswordAuthenticationToken 가져오기 (from token)
        UsernamePasswordAuthenticationToken authentication =
                getAuthentication(request);
        //4. 사용자 요청한 페이지로 이동
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String authorizationHeader = request.getHeader(
                env.getProperty("authorization.token.header.name")); //token

        if(authorizationHeader == null){ //token이 없거나 or token info(초기값)
            return null;
        } // Bearer [token]

        String token  = authorizationHeader.replace(env.getProperty("authorization.token.header.prefix"), "");

        String userId =  Jwts.parser()
                .setSigningKey(env.getProperty("token.secret"))
                .parseClaimsJws(token.trim())
                .getBody()
                .getSubject();  //token 해지

        if(userId == null){
            return null;
        }
        return  new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
    }
}
