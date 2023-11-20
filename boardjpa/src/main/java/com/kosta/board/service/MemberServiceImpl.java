package com.kosta.board.service;

import com.kosta.board.Entity.Member;
import com.kosta.board.dto.MemberDTO;
import com.kosta.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository repository;

    @Override
    public MemberDTO login(String id, String password) throws Exception {
        Optional<Member> omember = repository.findById(id);
        if(omember.isEmpty()) throw new Exception("계좌번호 오류");
        Member member = omember.get();
        if(!member.getPassword().equals(password)) throw new Exception("비밀번호 오류");
        return member.toDTO(); // entity -> dto
    }

    @Override
    public void join(MemberDTO memberDTO) throws Exception {
        Optional<Member> omember = repository.findById(memberDTO.getId());
        if(omember.isPresent()) throw new Exception("아이디 중복 오류");
        Member member = memberDTO.toEntity(); // dto -> entity
        repository.save(member);
    }
}
