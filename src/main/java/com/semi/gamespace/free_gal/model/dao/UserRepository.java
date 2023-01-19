package com.semi.gamespace.free_gal.model.dao;

import com.semi.gamespace.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserRepository {
    Optional<MemberDTO> findByEmailForSocialLogin(String email);
}
