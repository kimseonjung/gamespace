package com.semi.gamespace.authentication.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semi.gamespace.authentication.model.dto.SpaceUser;
import com.semi.gamespace.member.model.dao.MemberMapper;
import com.semi.gamespace.member.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService {
    private final MemberMapper memberMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    public OAuth2DetailsService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("OAuth2DetailsService.loadUser 호출");
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> userInfo = oAuth2User.getAttributes();
        try {
            System.out.println("-------------------- oAuth2User");
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(oAuth2User));
            System.out.println("-------------------- oAuth2User end");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String pwd = bCryptPasswordEncoder.encode("pwd"+uuid);
        String name = (String) userInfo.get("name");
        String username = "";
        String email = "";
        switch (provider) {
            case "google":
                username = "G" + (String) userInfo.get("sub");
                email = (String) userInfo.get("email");
                break;
            case "naver":
                Map<String, Object> response = oAuth2User.getAttribute("response");
                username = "N" + (String) response.get("id");
                email = (String) response.get("email");
                break;
            case "kakao":
                Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
                username = "K" + userInfo.get("id");
                email = (String) kakaoAccount.get("email");
                break;
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
        System.out.println(username + ", " + email + ", " + "N");
        if(memberMapper.SocialMemberEmailCheck(email) == 0) {
            MemberDTO socialNewMember = new MemberDTO();
            socialNewMember.setUserId(username);
            socialNewMember.setUserNickname(username);
            socialNewMember.setUserName(name);
            socialNewMember.setUserPwd(pwd);
            socialNewMember.setUserEmail(email);
            socialNewMember.setIsAdmin("N");
            System.out.println("newMember : " + socialNewMember);
            return new SpaceUser(socialNewMember, authorities);
        }

        return new SpaceUser(memberMapper.findMemberByEmailForSocialLogin(email), authorities);
    }
}
