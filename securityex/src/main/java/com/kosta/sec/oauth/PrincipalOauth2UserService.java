package com.kosta.sec.oauth;

import com.kosta.sec.auth.PrincipalDetails;
import com.kosta.sec.entity.User;
import com.kosta.sec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository repository;
    // userRequest는 code를 받아 accessToken을 응답받은 객체이다
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        System.out.println(userRequest.getAccessToken().getTokenValue());
//        System.out.println(userRequest.getClientRegistration());
//        System.out.println(super.loadUser(userRequest));
        OAuth2User oAuth2User = super.loadUser(userRequest);
        return pricessOAuth2User(userRequest, oAuth2User);
    }

    private OAuth2User pricessOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            System.out.println("네이버 로그인 요청");
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttribute("response"));
        } else if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
            System.out.println("카카오 로그인 요청");
        } else {
            System.out.println("네이버와 카카오만 지원합니다");
        }
        Optional<User> userOptional =
                repository.findByProviderAndProviderId(oAuth2UserInfo.getProvider(), oAuth2UserInfo.getProviderId());
        User user = null;
        if(userOptional.isPresent()) { // 이미 가입되어 있으면 update
            user = userOptional.get();
            user.setEmail(oAuth2UserInfo.getEmail());
            repository.save(user);
        } else { // 가입되어 있지 않으면 insert
            user.builder().username(oAuth2UserInfo.getProvider()+"_"+oAuth2UserInfo.getProviderId())
                    .email(oAuth2UserInfo.getEmail())
                    .roles("ROLE_USER")
                    .provider(oAuth2UserInfo.getProvider())
                    .providerId(oAuth2UserInfo.getProviderId())
                    .build();
            repository.save(user);
        }
//        return new PrincipalDetails(user, oAuth2User.getAttributes());
        return null;
    }
}
