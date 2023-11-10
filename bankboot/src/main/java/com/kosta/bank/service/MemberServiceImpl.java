package com.kosta.bank.service;

import com.kosta.bank.dao.AccountDAO;
import com.kosta.bank.dao.MemberDAO;
import com.kosta.bank.dto.Account;
import com.kosta.bank.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDAO memberDAO;

    @Override
    public void join(Member mem) throws Exception {
        memberDAO.insertMember(mem);
    }

    @Override
    public Member login(String id, String password) throws Exception {
        Member member = memberDAO.selectMember(id);
        if(member==null) throw new Exception("아이디가 틀립니다");
        if(!member.getPassword().equals(password)) throw new Exception("비밀번호가 틀립니다");
        member.setPassword("");
        return member;    }
}
