package com.kosta.sec.controller;

import com.kosta.sec.auth.PrincipalDetails;
import com.kosta.sec.entity.User;
import com.kosta.sec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;

@Controller
public class IndexController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping({"", "/"})
    @ResponseBody
    public String index() {
        return "인덱스";
    }
    @GetMapping("/user")
    @ResponseBody
    public String user(@AuthenticationPrincipal PrincipalDetails principal) {
        System.out.println("principal: " + principal);
        System.out.println(principal.getPassword());
        System.out.println(principal.getUsername());
        Iterator<? extends GrantedAuthority> iter = principal.getAuthorities().iterator();
        while(iter.hasNext()) {
            GrantedAuthority auth = iter.next();
            System.out.println(auth.getAuthority());
        }
        return "user";
    }
    @GetMapping("/admin")
//    @Secured("ROLE_MANAGE");
    @PreAuthorize("hasRole('ROLE_MANAGE')") // hasRole을 이용할 때 장점은 여러개를 사용할 수 있다 (or로 연결)
    @ResponseBody
    public String admin() {
        return "admin";
    }
    @GetMapping("/manager")
    @ResponseBody
    public String manager() {
        return "manager";
    }
    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("loginProc") // security가 가로채기 때문에 controller까지 오지 않는다
    public String loginPro(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        return "redirect:/";
    }

    @GetMapping("join")
    public String join() {
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProc(User user) {
        System.out.println(user);
        String rawPassword = user.getPassword();
        String encodePassword = encoder.encode(rawPassword);
        user.setRoles("ROLE_USER");
        user.setPassword(encodePassword);
        repository.save(user);
        return "redirect:/login";

    }
}
