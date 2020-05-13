package com.example.myappapiusers.security;


import com.example.myappapiusers.model.LoginRequestModel;
import com.example.myappapiusers.service.UserService;
import com.example.myappapiusers.shared.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService usersService;
    private Environment env;

    public AuthenticationFilter(UserService userService, Environment env, AuthenticationManager authenticationManager) {
        this.usersService = userService;
        this.env = env;
        super.setAuthenticationManager(authenticationManager);
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException{
        try{
            LoginRequestModel creds = new ObjectMapper()
                    .readValue(request.getInputStream(), LoginRequestModel.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>()
                    )
            );
        }catch (IOException ex){
            throw  new RuntimeException();
        }
    }

    //토큰 기반 인증 시스템 Bearer Tocken
    //JWT - 인증 헤더 내에서 사용되는 토큰 포맷, 두개의 시스템까지

    // 전통적인 인증 시스템의 문제점 : 세션과 쿠키는 모바일 애플리케이션에서 유효하게 사용할 수 없음 (공유 불가)
    //                              : 렌더링된 HTML 페이지가 반환되지만, 모바일 애플리케이션에서는 JSON(or XML)과 같은 포멧 필요
    @Override
    protected  void successfulAuthentication(HttpServletRequest req,
                                             HttpServletResponse res,
                                             FilterChain chain,
                                             Authentication authResult) throws IOException, ServletException{
        String email = ((User)authResult.getPrincipal()).getUsername();
        UserDto userDetail = usersService.getUserDetailsByEmail(email);

        //token expite_date (configuration or application.yml)
        String token = Jwts.builder()
                .compact();              // token 생성

        res.addHeader("token", token);
        res.addHeader("userId",userDetail.getUserId());
    }
}
