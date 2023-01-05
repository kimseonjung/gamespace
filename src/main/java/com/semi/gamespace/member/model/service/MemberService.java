package com.semi.gamespace.member.model.service;

import com.semi.gamespace.member.model.dao.MemberMapper;
import com.semi.gamespace.member.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {
    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public List<MemberDTO> selectAllMember() {
        return memberMapper.selectAllMember();
    }

    public boolean registMember(MemberDTO mem) throws Exception {
        int result = memberMapper.registMember();
        if(result <= 0) throw new Exception("회원 가입 중 오류가 발생했습니다.");
        return result > 0 ? true : false;
    }
}
