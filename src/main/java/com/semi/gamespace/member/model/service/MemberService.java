package com.semi.gamespace.member.model.service;

import com.semi.gamespace.admin.model.dto.SimpleMemberDTO;
import com.semi.gamespace.member.model.dao.MemberMapper;
import com.semi.gamespace.member.model.dto.FollowDTO;
import com.semi.gamespace.member.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public MemberDTO findMemberById(String userId) {
        return memberMapper.findMemberById(userId);
    }

    public int countFollowFromByCode(String userCode) {
        return memberMapper.countFollowFromByCode(userCode);
    }
    public int countFollowToByCode(String userCode) {
        return memberMapper.countFollowToByCode(userCode);
    }
    public boolean registMember(MemberDTO mem) throws Exception {
        /* 비밀번호 암호화 */
        String encodedPassword = passwordEncoder.encode(mem.getUserPwd());
        mem.setUserPwd(encodedPassword);
        int result = 0;
        try {
            System.out.println("다음 멤버 정보 등록을 시도합니다 : " + mem);
            result = memberMapper.registMember(mem);
            System.out.println("멤버 등록 완료");
        } catch (Exception e) {
            System.out.println("멤버 등록 실패 - 오류가 발생했습니다.");
        }
        System.out.println("등록 시도 결과 : " + result);
        return result > 0 ? true : false;
    }

    public boolean updateMember(MemberDTO mem) {
        int result = 0;
        try {
            System.out.println("다음 멤버 정보 수정을 시도합니다 : " + mem);
            result = memberMapper.updateMember(mem);
            System.out.println("멤버 수정 완료");
        } catch (Exception e) {
            System.out.println("멤버 수정 실패 - 오류가 발생했습니다.");
        }
        System.out.println("등록 시도 결과 : " + result);
        return result > 0 ? true : false;
    }

    public void updateMemberSiteLink(Map<String, String> linkAttribute) {
        int result = 0;
        try {
            System.out.println("다음 링크 수정을 시도합니다 : " + linkAttribute);
            result = memberMapper.updateMemberSiteLink(linkAttribute);
            System.out.println("링크 수정 완료");
        } catch (Exception e) {
            System.out.println("링크 수정 실패 - 오류가 발생했습니다.");
        }
        System.out.println("수정 시도 결과 : " + result);
    }

    public int countHistoryOfBoard(String memberCode) {
        return memberMapper.countHistoryOfBoard(memberCode);
    }

    public int countHistoryOfComment(String memberCode) {
        return memberMapper.countHistoryOfComment(memberCode);
    }

    public int checkFollowState(Map<String, String> followInfo) {
        int result = 1;
        result += memberMapper.checkFollowState(followInfo);
        return result;
    }
    public void insertFollowConnect(Map<String, String> codeData) {
        memberMapper.insertFollowConnect(codeData);
    }
    public void deleteFollowConnect(Map<String, String> codeData) {
        memberMapper.deleteFollowConnect(codeData);
    }

    public String findMemberId(Map<String, String> dataId) {
        return memberMapper.findMemberId(dataId);
    }

    public String findMemberForEmailSend(Map<String, String> dataPwd) {
        return memberMapper.findMemberForEmailSend(dataPwd);
    }

    public boolean registIdCheck(String inputId) {
        int result = 0;
        result += memberMapper.registIdCheck(inputId);

        return result==0 ? true : false;
    }

    public boolean registNicknameCheck(String inputNickname) {
        int result = 0;
        result += memberMapper.registNicknameCheck(inputNickname);

        return result==0 ? true : false;
    }

    public boolean registEmailCheck(String inputEmail) {
        int result = 0;
        result += memberMapper.registEmailCheck(inputEmail);

        return result==0 ? true : false;
    }

    public boolean memberPasswordIsMatch(MemberDTO currUser, String userLeave) {
        return passwordEncoder.matches(userLeave, currUser.getUserPwd());
    }

    public void leaveMemberByCode(String memberCode) {
        memberMapper.leaveMemberByCode(memberCode);
    }

    public void deleteFollowAll(String memberCode) {
        memberMapper.deleteFollowAll(memberCode);
    }

    public void updateMemberPassword(Map<String, String> data) {
        /* 비밀번호 암호화 */
        String encodedPassword = passwordEncoder.encode(data.get("userData"));
        data.put("userData", encodedPassword);
        memberMapper.updateMemberPassword(data);
    }

    public int countAllSearchedUser(Map<String, String> search) {
        return memberMapper.countAllSearchedUser(search);
    }
    public List<SimpleMemberDTO> findMemberUsingIndex(Map<String, String> search) {
        return memberMapper.findMemberUsingIndex(search);
    }

    public void memberBanByCode(Map<String, String> banData) {
        memberMapper.memberBanByCode(banData);
    }

    public void memberUnbanByCode(String memberCode) {
        memberMapper.memberUnbanByCode(memberCode);
    }
}
