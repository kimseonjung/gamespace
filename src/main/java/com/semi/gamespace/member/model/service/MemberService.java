package com.semi.gamespace.member.model.service;

import com.semi.gamespace.member.model.dao.MemberMapper;
import com.semi.gamespace.member.model.dto.FollowDTO;
import com.semi.gamespace.member.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
}
