package com.kosta.bank.repository;

import com.kosta.bank.entity.Account;
import com.kosta.bank.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
