package com.semi.gamespace.member.model.dao;

import com.semi.gamespace.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

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
    int checkFollowState(Map<String, String> followInfo);
    void insertFollowConnect(Map<String, String> codeData);
    void deleteFollowConnect(Map<String, String> codeData);
    String findMemberId(Map<String, String> dataId);
    String findMemberForEmailSend(Map<String, String> dataPwd);
    int registIdCheck(String inputId);
    int registNicknameCheck(String inputNickname);
    int registEmailCheck(String inputEmail);
    void leaveMemberByCode(String memberCode);
    void deleteFollowAll(String memberCode);
    void updateMemberPassword(Map<String, String> data);
}
