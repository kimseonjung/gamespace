package com.semi.gamespace.member.model.dao;

import com.semi.gamespace.authentication.model.dto.SpaceUser;
import com.semi.gamespace.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {
    MemberDTO findMemberById(String userId);
    MemberDTO findMemberByEmailForSocialLogin(String email); // OAuth2DetailsService.java
    int countFollowFromByCode(String userCode);
    int countFollowToByCode(String userCode);
    int registMember(MemberDTO member);
    int updateMember(MemberDTO member);
    int updateMemberSiteLink(Map<String, String> linkAttribute);
    int countHistoryOfBoard(String memberCode);
    int countHistoryOfComment(String memberCode);
    int SocialMemberEmailCheck(String email); // OAuth2DetailsService.java
}
