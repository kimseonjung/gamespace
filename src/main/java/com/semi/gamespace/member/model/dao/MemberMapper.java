package com.semi.gamespace.member.model.dao;

import com.semi.gamespace.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {
    MemberDTO findMemberById(String userId);
    int countFollowFromByCode(String userCode);
    int countFollowToByCode(String userCode);
    int registMember(MemberDTO member);
    int updateMember(MemberDTO member);
    int updateMemberSiteLink(Map<String, String> linkAttribute);
    int countHistoryOfBoard(String memberCode);
    int countHistoryOfComment(String memberCode);
}
