package com.kosta.bank.controller;

import com.kosta.bank.entity.Account;
import com.kosta.bank.entity.Member;
import com.kosta.bank.service.AccountService;
import com.kosta.bank.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MemberController {
    @Autowired
    private MemberService service;
    @PostMapping("join")
    public ResponseEntity<String> createMember(@RequestBody Member member) {
        try {
            service.join(member);
            return new ResponseEntity<>("회원가입 성공", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
