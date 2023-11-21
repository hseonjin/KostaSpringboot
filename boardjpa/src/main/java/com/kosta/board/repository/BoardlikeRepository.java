package com.kosta.board.repository;

import com.kosta.board.Entity.Boardlike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardlikeRepository extends JpaRepository<Boardlike, Integer> {
    Optional<Boardlike> findByMember_idAndBoard_num(String id, Integer num);
}
