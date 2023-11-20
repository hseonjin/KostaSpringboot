package com.kosta.board.service;

import com.kosta.board.dto.BoardDTO;
import com.kosta.board.util.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.List;

public interface BoardService {
    List<BoardDTO> boardListByPage(PageInfo pageInfo) throws Exception;

    BoardDTO boardDetail(Integer num) throws Exception;

    void readImage(Integer num, OutputStream out) throws Exception;

    Integer boardWrite(BoardDTO board, List<MultipartFile> files) throws Exception;

    Integer boardModify(BoardDTO board, List<MultipartFile> files) throws Exception;
    void boardDelete(Integer num) throws Exception;
    List<BoardDTO> searchListByPage(String type, String keyword, PageInfo pageInfo) throws Exception;
    Boolean isHeartBoard(String id, Integer num) throws Exception;
    void selHeartBoard(String id, Integer num) throws Exception;
    void delHeartBoard(String id, Integer num) throws Exception;
    void plusViewCount(Integer num) throws Exception;
}
