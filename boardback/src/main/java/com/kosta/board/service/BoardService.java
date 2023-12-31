package com.kosta.board.service;

import com.kosta.board.dto.Board;
import com.kosta.board.util.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public interface BoardService {
    List<Board> boardListByPage(PageInfo pageInfo) throws Exception;

    Board boardDetail(Integer num) throws Exception;

    void readImage(Integer num, OutputStream out) throws Exception;

    Integer boardWrite(Board board, List<MultipartFile> file) throws Exception;

    Integer boardModify(Board board, List<MultipartFile> file) throws Exception;
    void boardDelete(Integer num) throws Exception;
    List<Board> searchListByPage(String type, String keyword, PageInfo pageInfo) throws Exception;
    Boolean isHeartBoard(String id, Integer num) throws Exception;
    void selHeartBoard(String id, Integer num) throws Exception;
    void delHeartBoard(String id, Integer num) throws Exception;

}
