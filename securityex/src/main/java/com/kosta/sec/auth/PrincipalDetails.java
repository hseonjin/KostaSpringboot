package com.kosta.sec.auth;

import com.kosta.sec.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

// security가 /loginProc 주소를 낚아채서 로그인을 진행시킨다
// 로그인 진행이 완료가 되면 security session을 만들어 준다. (Security ContextHolder)
// security session에 들어가는 타입은 Authentication 타입의 객체여야 한다
// Authentication 안에 User 정보를 넣어야 한다
// 그 User 오브젝트 타입은 UserDetails 타입이어야 한다

public class PrincipalDetails implements UserDetails {
    private User user;
    private Map<String, Object> attributes;

    public PrincipalDetails(User user) {
        this.user = user;
    }
    public PrincipalDetails(User user, Map<String, Object> attributes;) {
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // 권한 (Roll)
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRoles();
            }
        });
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
