package com.kosta.bank.dao;

import com.kosta.bank.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper // 매퍼클래스로 생성될 것임을 알려주는 어노테이션
@Repository
public interface MemberDAO {
    Member selectMember(String id) throws Exception;
    void insertMember(Member member) throws Exception;
}
