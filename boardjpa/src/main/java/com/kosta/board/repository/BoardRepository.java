package com.kosta.board.repository;

import com.kosta.board.Entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
//    List<Board> findByWriter(String writer);
    Page<Board> findBySubjectContains(String subject, PageRequest pageRequest);
    Page<Board> findByContentContains(String content, PageRequest pageRequest);
    Page<Board> findByMember_Id(String writer, PageRequest pageRequest);
}
