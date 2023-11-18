package com.kosta.board.repository;

import com.kosta.board.Entity.Board;
import com.kosta.board.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
