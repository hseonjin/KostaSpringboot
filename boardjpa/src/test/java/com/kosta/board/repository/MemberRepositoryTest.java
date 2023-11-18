package com.kosta.board.repository;

import com.kosta.board.Entity.Board;
import com.kosta.board.Entity.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository repository;

    @Test
    void selectMember() {
        Member member = repository.findById("kkomee").get();
        System.out.println(member);
        for(Board board : member.getBoardList()) {
            System.out.println(board);
        }
    }
}