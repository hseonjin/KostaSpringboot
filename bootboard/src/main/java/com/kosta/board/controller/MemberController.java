package com.kosta.board.controller;

import com.kosta.board.dto.Member;
import com.kosta.board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {
    @Autowired
    private MemberService service;
    @Autowired
    private HttpSession session;

    @GetMapping("login")
    public String login() { return "login"; }

    @PostMapping("login")
    public String login(@RequestParam("id") String id,
                        @RequestParam("password") String password,
                        Model model) {
        try {
            Member member = service.login(id, password);
            session.setAttribute("user", member);
            return "main";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("err", e.getMessage());
            return "error";
        }
    }

    @GetMapping("join")
    public String join() { return "join"; }

    @GetMapping("logout")
    public String logout() {
        session.removeAttribute("user");
        return "main";
    }
}
