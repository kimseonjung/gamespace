package com.semi.gamespace.member.model.dao;

import com.semi.gamespace.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<MemberDTO> selectAllMember();
    int registMember();
}
