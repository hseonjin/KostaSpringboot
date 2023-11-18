package com.kosta.bank.service;

import com.kosta.bank.dto.AccountDTO;
import com.kosta.bank.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    void makeAccount(Account acc) throws Exception;
//    void makeAccount(AccountDTO acc) throws Exception;
    Account accountInfo(String id) throws Exception;
    List<Account> allAccountInfo() throws Exception;
    Integer deposit(String id, Integer balance) throws Exception;
    Integer withdraw(String id, Integer balance) throws Exception;
}
