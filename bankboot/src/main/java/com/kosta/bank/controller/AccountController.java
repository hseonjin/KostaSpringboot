package com.kosta.bank.controller;

import com.kosta.bank.dto.Account;
import com.kosta.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private AccountService service;
    @GetMapping("main")
    public String main() { return "main"; }

    @GetMapping("makeAccount")
    public String makeAccount() { return "makeAccount"; }

    @PostMapping("makeAccount")
    public ModelAndView makeAccount(@ModelAttribute Account acc) {
        ModelAndView mav = new ModelAndView();
        try {
            service.makeAccount(acc);
            Account sacc = service.accountInfo(acc.getId());
            mav.addObject("acc", sacc);
            mav.setViewName("accountInfo");
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @GetMapping("deposit")
    public String deposit() { return "deposit"; }

    @PostMapping("deposit")
    public ModelAndView deposit(@RequestParam("id") String id, @RequestParam("balance") Integer balance) {
        ModelAndView mav = new ModelAndView();
        try {
            service.deposit(id, balance);
            Account acc = service.accountInfo(id);
            mav.addObject("acc", acc);
            mav.setViewName("accountInfo");
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @GetMapping("withdraw")
    public String withdraw() { return "withdraw"; }

    @PostMapping("withdraw")
    public ModelAndView withdraw(@RequestParam("id") String id, @RequestParam("balance") Integer balance) {
        ModelAndView mav = new ModelAndView();
        try {
            service.withdraw(id, balance);
            Account acc = service.accountInfo(id);
            mav.addObject("acc", acc);
            mav.setViewName("accountInfo");
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }
    @GetMapping("accountInfo")
    public String accountInfo() { return "accountInfoForm"; }

    @PostMapping("accountInfo")
    public ModelAndView accountInfo(@RequestParam("id") String id) {
        ModelAndView mav = new ModelAndView();
        try {
            Account acc = service.accountInfo(id);
            mav.addObject("acc", acc);
            mav.setViewName("accountInfo");
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @GetMapping("allAccountInfo")
    public ModelAndView allAccountInfo() {
        ModelAndView mav = new ModelAndView();
        try {
            List<Account> accs = service.allAccountInfo();
            mav.addObject("accs", accs);
            mav.setViewName("allAccountInfo");
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }
}
