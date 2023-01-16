package com.semi.gamespace.member.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = {"/member/login/*", "/member/regist/*", "/member/findId/*", "/member/findPwd/*", "/member/insert/*"})
public class loginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        System.out.println("memberFilter() - 호출됨");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(!request.getRequestURI().contains("/member/login/social")) { //예외 URL
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth.getPrincipal() != "anonymousUser") {
                System.out.println("memberFilter() - 비정상적인 접근을 감지하였습니다.");
                response.sendRedirect("http://localhost:8001");
            };
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
