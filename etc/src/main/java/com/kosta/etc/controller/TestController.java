package com.kosta.etc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {
    @GetMapping("request_test")
    public String requestTest() {
        return "request_test";
    }
    @PostMapping("request_test")
    public String requestTest(@RequestParam("data") String data) {
        return "response_test";
    }
    // response_test // forward 방식으로 주는 것이다

}
