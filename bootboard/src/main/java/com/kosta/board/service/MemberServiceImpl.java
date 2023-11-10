package com.kosta.board.service;

import com.kosta.board.dao.BoardDAO;
import com.kosta.board.dao.MemberDAO;
import com.kosta.board.dto.Board;
import com.kosta.board.dto.Member;
import com.kosta.board.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDAO memberDAO;

    @Override
    public Member login(String id, String password) throws Exception {
        Member member = memberDAO.selectMember(id);
        if(member==null) throw new Exception("아이디가 틀렸습니다"); // 해당 id로 등록된 회원정보가 없는 경우
        if(!member.getPassword().equals(password))
            throw new Exception("비밀번호가 틀렸습니다"); // 입력한 pw와 db의 pw 비교하여 일치하지 않는 경우
        return member;
    }

    @Override
    public void join(Member member) throws Exception {
        Member smem = memberDAO.selectMember(member.getId());
        if(smem!=null) throw new Exception("아이디 중복 오류");
        memberDAO.insertMember(member);
    }
}
