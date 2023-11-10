package com.kosta.bank.service;

import com.kosta.bank.dao.AccountDAO;
import com.kosta.bank.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDAO accountDAO;
    @Override
    public void makeAccount(Account acc) throws Exception {
        Account sacc = accountInfo(acc.getId());
        if(sacc != null) throw new Exception("계좌번호 중복");
        accountDAO.insertAccount(acc);
    }
    @Override
    public Account accountInfo(String id) throws Exception {
        return accountDAO.selectAccount(id);
    }

    @Override
    public void deposit(String id, Integer balance) throws Exception {
        Account acc = accountInfo(id);
        Integer deposit = acc.getBalance() + balance;
        accountDAO.updateBalance(id, deposit);
    }

    @Override
    public void withdraw(String id, Integer balance) throws Exception {
        Account acc = accountInfo(id);
        Integer withdraw = acc.getBalance() - balance;
        accountDAO.updateBalance(id, withdraw);
    }

    @Override
    public List<Account> allAccountInfo() throws Exception {
        return accountDAO.selectAccountList();
    }
}
