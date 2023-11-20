package com.kosta.board.repository;

import com.kosta.board.Entity.Board;
import com.kosta.board.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByEmail(String email);
}
