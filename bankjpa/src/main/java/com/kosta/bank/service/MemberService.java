package com.kosta.bank.service;

import com.kosta.bank.entity.Account;
import com.kosta.bank.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    void join(Member member) throws Exception;
    Member login(String id, String password) throws Exception;
}
