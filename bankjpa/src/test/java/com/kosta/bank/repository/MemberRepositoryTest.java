package com.kosta.bank.repository;

import com.kosta.bank.entity.Account;
import com.kosta.bank.entity.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository repository;

    @Test
    public void selectMemberTest() {
        Optional<Member> member =  repository.findById("hong");
        if(member.isPresent()) {
            System.out.println(member.get());
        }
    }

    @Test
    public void insertMemberTest() {
        Member member = new Member("moon", "문", "123", "email", "seoul");
        repository.save(member);
    }

    @Test
    public void loginTest() {
        Optional<Member> member =  repository.findById("hong");
        if(member.get().getPassword().equals("123")) {
            member.get();
        }
    }
}