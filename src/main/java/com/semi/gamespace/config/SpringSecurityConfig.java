package com.semi.gamespace.config;

import com.semi.gamespace.authentication.AuthenticationService;
import com.semi.gamespace.authentication.model.service.OAuth2DetailsService;
import com.semi.gamespace.authentication.controller.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@EnableWebSecurity
public class SpringSecurityConfig {
    private final AuthenticationService authenticationService;
    private final AuthenticationConfig authenticationConfig;
    private final OAuth2DetailsService oAuth2DetailsService;

    @Autowired
    public SpringSecurityConfig(AuthenticationService authenticationService, AuthenticationConfig authenticationConfig,
                                OAuth2DetailsService oAuth2DetailsService) {
        this.authenticationService = authenticationService;
        this.authenticationConfig = authenticationConfig;
        this.oAuth2DetailsService = oAuth2DetailsService;
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
        List<String> anonymousPermitList = permitListMap.get("anonymousPermitList");

        http.csrf().disable()
                .authorizeHttpRequests()
                //antMatchers에 해당하는 path 대상
                .antMatchers(adminPermitList.toArray(new String[adminPermitList.size()])).hasRole("ADMIN")
                //관리자 접근 허용
                .antMatchers(memberPermitList.toArray(new String[memberPermitList.size()])).hasAnyRole("MEMBER", "ADMIN")
                //멤버, 관리자 접근 허용
                .anyRequest().permitAll()
                //anyRequest() : 그 외의 path는
                //permitAll() : 모든 유저의 접근을 허용

                /* 일반 로그인 */
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

                /* 로그아웃 */
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

                /* 예외처리 */
                .and()
                .exceptionHandling()
                .accessDeniedPage(authenticationConfig.getAccessDeniedUrl())

                /* 소셜 로그인 */
                .and()
                .oauth2Login()
                //OAuth2 로그인 설정 진입점
//                .loginPage("/oauth2/authorization/google")
                .successHandler(new LoginSuccessHandler())
                //성공 시 넘어갈 페이지 Handler
                .userInfoEndpoint()
                //OAuth2 로그인 성공 후 사용자 정보를 가져오는 설정
                .userService(oAuth2DetailsService)
                .and();
                //로그인 성공 후속 조치를 진행할 UserService 등록

                /* 세션 관리 */
        http.sessionManagement()
                .maximumSessions(1)
                //세션 최대 허용 수
                .maxSessionsPreventsLogin(false)
                //중복 로그인 방지 여부 - false : 이전 사용자의 세션 만료
                .expiredUrl("/common/error/expired");
                //세션이 만료된 경우 페이지 처리

        return http.build();
    }
}
