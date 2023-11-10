package com.kosta.bank.dao;

import com.kosta.bank.dto.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountDAO {
    void insertAccount(Account acc) throws Exception; // 매퍼의 id와 동일해야 함
    Account selectAccount(String id) throws Exception;
    void updateBalance(@Param("id") String id, @Param("balance") Integer balance) throws Exception;
    List<Account> selectAccountList() throws Exception;
}
