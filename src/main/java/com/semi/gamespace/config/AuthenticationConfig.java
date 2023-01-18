package com.semi.gamespace.config;

import com.semi.gamespace.authentication.model.dto.SpaceUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AuthenticationConfig {
    public List<String> getAdminPermitList() {
        List<String> adminPermitList = new ArrayList<>();
        /* 관리자만 접근 가능한 url */
        adminPermitList.add("/admin/dashboard"); //ex
        adminPermitList.add("/admin/**");   //관리자 전용 페이지
        adminPermitList.add("/freeGal/freeGalListNotice"); //공지사항 리스트만 및 공지사항 작성 메뉴
        adminPermitList.add("/news/newsInsert"); //뉴스 작성
        adminPermitList.add("/news/newsUpdate"); //뉴스 수정
        adminPermitList.add("/game/gameInfoInsert"); //게임정보추가

        return adminPermitList;
    }

    public List<String> getMemberPermitList() {
        /* 관리자와 회원만 접근 가능한 url */
        List<String> memberPermitList = new ArrayList<>();
        memberPermitList.add("/member/dashboard"); //ex
        memberPermitList.add("/member/userSetting");    //개인정보 수정
        memberPermitList.add("/member/update/**");      //개인정보 수정
        memberPermitList.add("/freeGal/upload"); //게시글 작성 파트
        memberPermitList.add("/freeGal/view"); //게시글 상세보기 파트
        memberPermitList.add("/support/supportList"); //고객센터 리스트 파트
        memberPermitList.add("/news/news/**"); //뉴스 파트
        memberPermitList.add("/news/newsDetail"); //뉴스 상세보기 파트
        memberPermitList.add("/game/game");//게임메인
        memberPermitList.add("/game/gameInfoDetail");//게임정보디테일

        return memberPermitList;
    }

    public String getLoginSuccessUrl() {
        return "/";
    }
    public String getLoginFailureUrl() {
        return "/member/login?error=1";
    }
    public String getLogoutUrl() {
        return "/member/logout";
    }
    public String getAccessDeniedUrl() {
        return "/common/error/denied";
    }

    public SpaceUser getAuthenticationUser() {
        SpaceUser member = (SpaceUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return member;
    }
}
