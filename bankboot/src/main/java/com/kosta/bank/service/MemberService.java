package com.kosta.bank.service;

import com.kosta.bank.dto.Account;
import com.kosta.bank.dto.Member;

import java.util.List;

public interface MemberService {
    void join(Member mem) throws Exception;
    Member login(String id, String password) throws Exception;
}
