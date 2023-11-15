package com.kosta.board.controller;

import com.kosta.board.dto.Board;
import com.kosta.board.service.BoardService;
import com.kosta.board.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BoardController {
    @Autowired
    private BoardService service;

    // 게시글 리스트
    @GetMapping("boardList/{page}")
    public ResponseEntity<Object> boardList(@PathVariable(required = false) Integer page) {
        try {
            PageInfo pageInfo = new PageInfo(page);
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
    @GetMapping("boarddetail/{num}")
    public ResponseEntity<Board> boardDetail(@PathVariable Integer num) {
        try {
            Board board = service.boardDetail(num);
            return new ResponseEntity<>(board, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("img/{num}")
    public void imageView(@PathVariable Integer num, HttpServletResponse response) {
        try {
            service.readImage(num, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 게시글 삭제
    @DeleteMapping("boarddelete/{num}")
    public ResponseEntity<Integer> boardDelete(@PathVariable Integer num) {
        try {
            service.boardDelete(num);
            return new ResponseEntity<>(num, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 검색
//    @GetMapping("boardsearch")
//    public ResponseEntity<Object> boardSearch(@RequestBody Map<String, String> param) {
//        try {
//            PageInfo pageInfo = new PageInfo();
//            pageInfo.setCurPage(Integer.valueOf(param.get("page")));
//            List<Board> boardList = service.searchListByPage(param.get("type"), param.get("keyword"), pageInfo);
//            return new ResponseEntity<>(boardList, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("검색 실패" ,HttpStatus.BAD_REQUEST);
//        }
//    }
    @GetMapping("boardsearch/{page}/{type}/{keyword}")
    public ResponseEntity<Object> boardSearch(@PathVariable(required = false) Integer page,
                                              @PathVariable(required = false) String type,
                                              @PathVariable(required = false) String keyword) {
        try {
            PageInfo pageInfo = new PageInfo(page);
            List<Board> boardList = service.searchListByPage(type, keyword, pageInfo);
            Map<String, Object> res = new HashMap<>();
            res.put("boardList", boardList);
            res.put("pageInfo", pageInfo);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("게시글 검색 실패", HttpStatus.BAD_REQUEST);
        }
    }

    // 게시글 작성
    @PostMapping("boardwrite")
    public ResponseEntity<Integer> boardWrite(@ModelAttribute Board board, MultipartFile file) {
        try {
            Integer num = service.boardWrite(board, file);
            return new ResponseEntity<>(num, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
