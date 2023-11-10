package com.kosta.bank.controller;

import com.kosta.bank.dto.Member;
import com.kosta.bank.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String login() {
        return "login";
    }
    @PostMapping("login")
    public ModelAndView login(@RequestParam("id") String id, @RequestParam("password") String password) {
        ModelAndView mav = new ModelAndView();
        try {
            Member mem = service.login(id, password);
            session.setAttribute("user", mem);
            mav.setViewName("makeAccount");
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @GetMapping("join")
    public String join() {
        return "join";
    }
    @PostMapping("join")
    public ModelAndView join(@ModelAttribute Member mem) {
        ModelAndView mav = new ModelAndView();
        try {
            service.join(mem);
            mav.addObject("member", mem);
            mav.setViewName("login");
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }
    @GetMapping("logout")
    public String logout() {
        session.removeAttribute("id");
        return "main";
    }

}
