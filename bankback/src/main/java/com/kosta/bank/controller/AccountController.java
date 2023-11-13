package com.kosta.bank.controller;

import com.kosta.bank.dto.Account;
import com.kosta.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController // view를 주지 않겠다, 리턴으로 주는 것들은 모두 데이터
public class AccountController {
    @Autowired
    private AccountService service;

    @PostMapping("makeAccount")
    public ResponseEntity<String> makeAccount(@RequestBody Account acc) {
        try {
            service.makeAccount(acc);
            return new ResponseEntity<>("계좌가 개설되었습니다", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("계좌개설에 실패했습니다", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("accountInfo/{id}") // Account는 Object의 자식 - Object 타입일 때는 메시지와 객체 모두를 보내줄 수 있음
    public ResponseEntity<Object> accountInfo(@PathVariable String id) {
        try {
            Account acc = service.accountInfo(id);
            return new ResponseEntity<Object>(acc, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("deposit") // post 방식의 통신
    public ResponseEntity<Object> deposit(@RequestBody Account acc) {
        try {
            service.deposit(acc.getId(), acc.getBalance());
            Account uacc = service.accountInfo(acc.getId());
            return new ResponseEntity<Object>(uacc, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("withdraw")
    public ResponseEntity<Object> withdraw(@RequestBody Account acc) {
        try {
            service.withdraw(acc.getId(), acc.getBalance());
            Account uacc = service.accountInfo(acc.getId());
            return new ResponseEntity<Object>(uacc, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
}