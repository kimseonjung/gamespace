package com.semi.gamespace.authentication;

import com.semi.gamespace.authentication.model.dto.spaceUser;
import com.semi.gamespace.config.AuthenticationConfig;
import com.semi.gamespace.member.model.dao.MemberMapper;
import com.semi.gamespace.member.model.dto.MemberDTO;
import com.semi.gamespace.member.model.dto.MemberRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final MemberMapper memberMapper;

    @Autowired
    public AuthenticationServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        MemberDTO member = memberMapper.findMemberById(userId);
        System.out.println(member);

        if(member == null) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(
                member.getIsAdmin().equals("Y") ? "ROLE_ADMIN" : "ROLE_MEMBER")
        );

        return new spaceUser(member, authorities);
    }

    @Override
    public Map<String, List<String>> getPermitListMap() {
        Map<String, List<String>> permitListMap = new HashMap<>();
        List<String> adminPermitList = new AuthenticationConfig().getAdminPermitList();
        List<String> memberPermitList = new AuthenticationConfig().getMemberPermitList();

        permitListMap.put("adminPermitList", adminPermitList);
        permitListMap.put("memberPermitList", memberPermitList);
        return permitListMap;
    }
}
