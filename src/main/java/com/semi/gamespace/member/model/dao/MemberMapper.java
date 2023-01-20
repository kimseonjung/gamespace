package com.semi.gamespace.member.model.dao;

import com.semi.gamespace.admin.model.dto.SimpleMemberDTO;
import com.semi.gamespace.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {
    MemberDTO findMemberById(String userId);
    MemberDTO findMemberByCode(String code);
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
    List<Map<String, String>> findMemberSiteLinkList(String refCode);
    int registIdCheck(String inputId);
    int registNicknameCheck(String inputNickname);
    int registEmailCheck(String inputEmail);
    void leaveMemberByCode(String memberCode);
    void deleteFollowAll(String memberCode);
    void updateMemberPassword(Map<String, String> data);
    int countAllSearchedUser(Map<String, String> search);

    List<SimpleMemberDTO> findMemberUsingIndex(Map<String, String> search);
    void memberBanByCode(Map<String, String> banData);

    void memberUnbanByCode(String memberCode);

    int countAllFollowUser(Map<String, String> searched);

    List<String> findFollowerUsingIndex(Map<String, String> findMap);
    List<String> findFollowingUsingIndex(Map<String, String> findMap);

    String getFollowDate(Map<String, String> followMap);

}
