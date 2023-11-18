package com.kosta.bank.service;

import com.kosta.bank.dto.AccountDTO;
import com.kosta.bank.entity.Account;
import com.kosta.bank.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void makeAccount(Account acc) throws Exception {
//    public void makeAccount(AccountDTO acc) throws Exception {
        Optional<Account> oacc = repository.findById(acc.getId());
        if(oacc.isPresent()) throw new Exception("계좌번호 중복 오류");
        // modelMapper 쓰는 경우
//        Account accEntity = modelMapper.map(acc, Account.class); // 앞 것이 원본, 뒤가 바꾸고자하는 클래스명
//        repository.save(accEntity);
        // modelMapper 쓰는 경우 끝
        repository.save(acc);
    }

    @Override
    public Account accountInfo(String id) throws Exception {
        Optional<Account> oacc =  repository.findById(id);
        if(oacc.isEmpty()) throw new Exception("존재하지 않는 계좌번호");
        return oacc.get();
    }

    @Override
    public List<Account> allAccountInfo() throws Exception {
        return repository.findAll();
    }

    @Override
    public Integer deposit(String id, Integer balance) throws Exception {
        Optional<Account> oacc =  repository.findById(id);
        if(oacc.isEmpty()) throw new Exception("존재하지 않는 계좌번호");
        Account acc = oacc.get();
        acc.deposit(balance);
        repository.save(acc);
        return acc.getBalance();
    }

    @Override
    public Integer withdraw(String id, Integer balance) throws Exception {
        Optional<Account> oacc =  repository.findById(id);
        if(oacc.isEmpty()) throw new Exception("존재하지 않는 계좌번호");
        Account acc = oacc.get();
        acc.withdraw(balance);
        repository.save(acc);
        return acc.getBalance();
    }
}
