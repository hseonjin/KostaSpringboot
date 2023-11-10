package com.kosta.board.controller;

import com.kosta.board.dto.Board;
import com.kosta.board.service.BoardService;
import com.kosta.board.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class BoardController {
    @Autowired
    private BoardService service;

    @GetMapping("/")
    public String board() {
        return "main";
    }

    @GetMapping({"boardlist/{page}", "boardlist"})
    public ModelAndView boardList(@PathVariable(required = false) Integer page) {
        ModelAndView mav = new ModelAndView();
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurPage(page == null ? 1 : page);
        try {
            List<Board> boardList = service.boardListByPage(pageInfo);
            mav.addObject("boardList", boardList);
            mav.addObject("pageInfo", pageInfo);
            mav.setViewName("boardlist");
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @GetMapping("boarddetail/{num}/{page}")
    public ModelAndView boardDetail(@PathVariable(name = "num") Integer num, @PathVariable(name = "page") Integer page) {
        ModelAndView mav = new ModelAndView();
        try {
            Board board = service.boardDetail(num);
            mav.addObject("board", board);
            mav.addObject("page", page);
            mav.setViewName("detailform");
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", "게시글 상세 조회 실패");
            mav.setViewName("error");
        }
        return mav;
    }

    @GetMapping("image/{num}")
    public void imageView(@PathVariable(name = "num") Integer num, HttpServletResponse response) {
        try {
            service.readImage(num, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("boardwrite")
    public String boardWrite() {
        return "writeform";
    }

    @PostMapping("boardwrite")
    public ModelAndView boardWrite(@ModelAttribute Board board, MultipartFile file) {
        ModelAndView mav = new ModelAndView();
        try {
            service.boardWrite(board, file);
            mav.setViewName("detailform");
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", "게시글 상세 조회 실패");
            mav.setViewName("error");
        }
        return mav;
    }

    @GetMapping("boardmodify/{num}/{page}")
    public ModelAndView boardModify(@PathVariable("num") Integer num, @PathVariable("page") Integer page) {
        ModelAndView mav = new ModelAndView();
        try {
            Board board = service.boardDetail(num);
            mav.addObject("board", board);
            mav.setViewName("modifyform");
        } catch (Exception e) {
            mav.addObject("err", "글 수정 오류");
            mav.setViewName("error");

        }
        return mav;
    }

    @PostMapping("boardmodify") // 게시글 수정 페이지 POST
    public ModelAndView boardModify(@ModelAttribute Board board, MultipartFile file, @RequestParam("page") Integer page) {
        ModelAndView mav = new ModelAndView();
        try {
            service.boardModify(board, file);
            mav.setViewName("detailform"); // 수정 완료 후 디테일페이지 이동
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @GetMapping("boarddelete/{num}/{page}")
    public String boardDelete(@PathVariable Integer num, @PathVariable Integer page) {
        try {
            service.boardDelete(num);
            return "redirect:/boardlist?page=" + page;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("boardsearch") // 검색한 게시글 리스트 POST
    public ModelAndView boardModify(@RequestParam("type") String type,
                                    @RequestParam("keyword") String keyword,
                                    @RequestParam(value="page", required=false, defaultValue = "1") Integer page) {
        ModelAndView mav = new ModelAndView();
        try {
            PageInfo pageInfo = new PageInfo();
            pageInfo.setCurPage(page);
            if(type.equals("all")) { mav.setViewName("boardlist"); }
            List<Board> boardList = service.searchListByPage(type, keyword, pageInfo);
            mav.addObject("boardList", boardList);
            mav.addObject("pageInfo", pageInfo);
            mav.addObject("type", type);
            mav.addObject("keyword", keyword);
            mav.setViewName("boardlist");

        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", "검색 오류");
            mav.setViewName("error");
        }
        return mav;
    }
}