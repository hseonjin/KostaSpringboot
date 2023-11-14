package com.kosta.board.controller;

import com.kosta.board.dto.Board;
import com.kosta.board.service.BoardService;
import com.kosta.board.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BoardController {
    @Autowired
    private BoardService service;

    // 게시글 리스트
    @GetMapping({"boardList/{page}", "boardList"})
    public ResponseEntity<Object> allAccountInfo(@PathVariable(required = false) Integer page) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurPage(page == null ? 1 : page);
        try {
            List<Board> boardList = service.boardListByPage(pageInfo);
            Map<String, Object> res = new HashMap<>();
            res.put("boardList", boardList);
            res.put("pageInfo", pageInfo);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>("게시글 리스트 조회 실패", HttpStatus.BAD_REQUEST);
        }
    }
    // 상세보기
    @GetMapping("boarddetail/{num}/{page}")
    public ResponseEntity<Object> boardDetail(@PathVariable Integer num, @PathVariable Integer page) {
        try {
            Map<String, Object> res = new HashMap<>();
            res.put("board", service.boardDetail(num));
            res.put("page", page);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("게시글 상세 조회 실패", HttpStatus.BAD_REQUEST);
        }
    }

    // 게시글 삭제
    @GetMapping("boarddelete/{num}/{page}")
    public ResponseEntity<String> boardDelete(@PathVariable Integer num, @PathVariable Integer page) {
        try {
            service.boardDelete(num);
            return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("삭제 실패", HttpStatus.BAD_REQUEST);
        }
    }

    // 검색
    @PostMapping("boardsearch")
    public ResponseEntity<Object> boardSearch(@RequestBody Map<String, String> param) {
//        @RequestBody String type,
//        @RequestBody String keyword,
//        @RequestBody Integer page
        try {
            PageInfo pageInfo = new PageInfo();
            pageInfo.setCurPage(Integer.valueOf(param.get("page")));
            List<Board> boardList = service.searchListByPage(param.get("type"), param.get("keyword"), pageInfo);
            return new ResponseEntity<>(boardList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("검색 실패" ,HttpStatus.BAD_REQUEST);
        }
    }
}
