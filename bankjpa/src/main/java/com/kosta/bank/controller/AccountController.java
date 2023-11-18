package com.kosta.bank.controller;

import com.kosta.bank.dto.AccountDTO;
import com.kosta.bank.entity.Account;
import com.kosta.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private AccountService service;
    @PostMapping("makeAccount")
    public ResponseEntity<String> makeAccount(@RequestBody Account acc) {
        try {
            service.makeAccount(acc);
            return new ResponseEntity<>(acc.getId()+" 계좌가 개설되었습니다", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("accountInfo/{id}")
    public ResponseEntity<Object> accountInfo(@PathVariable("id") String id) { // RequestParam
        try {
            Account acc = service.accountInfo(id);
            return new ResponseEntity<>(acc, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("allAccountInfo")
    public ResponseEntity<Object> allAccountInfo() {
        try {
            List<Account> accs = service.allAccountInfo();
            return new ResponseEntity<Object>(accs, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>("계좌 목록 조회 실패", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("deposit")
//    public ResponseEntity<Object> deposit(@RequestBody Account acc) {
    public ResponseEntity<Object> deposit(@RequestBody AccountDTO acc) {
        try {
            Integer balance = service.deposit(acc.getId(), acc.getBalance());
            Map<String, Object> res = new HashMap<>();
            res.put("id", acc.getId());
            res.put("balance", balance);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("withdraw")
    public ResponseEntity<Object> withdraw(@RequestBody Account acc) {
        try {
            Integer balance = service.withdraw(acc.getId(), acc.getBalance());
            Map<String, Object> res = new HashMap<>();
            res.put("id", acc.getId());
            res.put("balance", balance);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
