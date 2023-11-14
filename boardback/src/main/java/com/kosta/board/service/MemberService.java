package com.kosta.board.service;

import com.kosta.board.dto.Board;
import com.kosta.board.dto.Member;
import com.kosta.board.util.PageInfo;

import java.util.List;

public interface MemberService {
    Member login(String id, String password) throws Exception;
    void join(Member member) throws Exception;
}
