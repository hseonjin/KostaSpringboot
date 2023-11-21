package com.kosta.board.repository;

import com.kosta.board.Entity.Board;
import com.kosta.board.Entity.Boardlike;
import com.kosta.board.Entity.Member;
import com.kosta.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class BoardLikeRepositoryTest {
    @Autowired
    BoardlikeRepository repository;
    @Autowired
    BoardService service;

    @Test
    void isHeartSelected() {
        Optional<Boardlike> boardLike = repository.findByMember_idAndBoard_num("kkomee", 58);
        if(boardLike.isPresent()) {
            System.out.println(boardLike);
        } else {
            System.out.println("없음");
        }
    }
    @Test
    void selHeartBoard() {
        Boardlike boardlike = Boardlike.builder()
                .member(Member.builder().id("kkomee").build())
                .board(Board.builder().num(70).build())
                .build();
        repository.save(boardlike);
    }

    @Test
    void delHeartBoard() {
        Optional<Boardlike> boardlike = repository.findByMember_idAndBoard_num("hong", 60);
        repository.deleteById(boardlike.get().getNum());
    }

    @Test
    void isHeartSelectedService() {
        try {
            System.out.println(service.isHeartBoard("kkomee", 23));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void setHeartBoardService() {
        try {
            System.out.println(service.selHeartBoard("hong", 23));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}