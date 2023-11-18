package com.kosta.bank.service;

import com.kosta.bank.entity.Account;
import com.kosta.bank.entity.Member;
import com.kosta.bank.repository.AccountRepository;
import com.kosta.bank.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository repository;

    @Override
    public void join(Member member) throws Exception {
        Optional<Member> omember = repository.findById(member.getId());
        if(omember.isPresent()) throw new Exception("아이디 중복 오류");
        repository.save(member);
    }

    @Override
    public Member login(String id, String password) throws Exception{
        Optional<Member> omember = repository.findById(id);
        if(omember.isEmpty()) throw new Exception("아이디 오류");
        Member member = omember.get();
        if(!member.getPassword().equals(password)) throw new Exception("비밀번호 오류");
        return member;
    }
}
