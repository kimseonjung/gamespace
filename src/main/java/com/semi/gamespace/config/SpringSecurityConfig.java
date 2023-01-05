package com.semi.gamespace.config;

import com.semi.gamespace.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;
import java.util.Map;

@EnableWebSecurity
public class SpringSecurityConfig {
    private final AuthenticationService authenticationService;
    private final AuthenticationConfig authenticationConfig;

    @Autowired
    public SpringSecurityConfig(AuthenticationService authenticationService, AuthenticationConfig authenticationConfig) {
        this.authenticationService = authenticationService;
        this.authenticationConfig = authenticationConfig;
    }

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().mvcMatchers(
                "/css/**", "/js/**", "/image/**"
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        Map<String, List<String>> permitListMap = authenticationService.getPermitListMap();
        List<String> adminPermitList = permitListMap.get("adminPermitList");
        List<String> memberPermitList = permitListMap.get("memberPermitList");

        http.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers(memberPermitList.toArray(new String[memberPermitList.size()])).hasAnyRole("MEMBER", "ADMIN")
                .antMatchers(adminPermitList.toArray(new String[adminPermitList.size()])).hasRole("ADMIN")
                //antMatchers에 해당하는 path 대상
                //hasRole("ADMIN") : 관리자 접근 허용
                .anyRequest().permitAll()
                //anyRequest() : 그 외의 path는
                //permitAll() : 모든 유저의 접근을 허용

                .and()
                .formLogin()
                //form 기반 인증 - 로그인 정보는 기본적으로 HttpSession을 이용
                .loginPage("/member/login")
                //기본 제공되는 form 말고 커스텀 form 사용
                .usernameParameter("userId")
                .passwordParameter("password")
                .successForwardUrl(authenticationConfig.getLoginSuccessUrl())
                //로그인 성공 시 URL 처리
                .failureUrl(authenticationConfig.getLoginFailureUrl())
                //로그인 실패 시 URL 처리

                .and()
                .logout()
                //로그아웃을 지원하는 메소드
                .logoutRequestMatcher(new AntPathRequestMatcher(authenticationConfig.getLogoutUrl()))
                //로그아웃 URL을 /logout이 아닌 다른 URL로 재정의
                .deleteCookies("JSESSIONID")
                //특정 "KEY명" 쿠키 제거
                .invalidateHttpSession(true)
                //로그아웃 시 HTTP세션 초기화(만료) 시킴
                .logoutSuccessUrl("/")

                .and()
                .exceptionHandling()
                .accessDeniedPage(authenticationConfig.getAccessDeniedUrl());

        return http.build();
    }
}
