package com.kosta.board.service;

import com.kosta.board.Entity.Member;
import com.kosta.board.dto.MemberDTO;

public interface MemberService {
    MemberDTO login(String id, String password) throws Exception;
    void join(MemberDTO member) throws Exception;
}
