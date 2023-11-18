package com.kosta.board.repository;

import com.kosta.board.Entity.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BoardRepositoryTest {
    @Autowired
    private BoardRepository repository;
    @Test
    void selectBoard() {
        Board board = repository.findById(61).get();
        System.out.println(board);
        System.out.println(board.getMember());
    }

}