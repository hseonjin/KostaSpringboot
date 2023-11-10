package com.kosta.board.service;

import com.kosta.board.dao.BoardDAO;
import com.kosta.board.dto.Board;
import com.kosta.board.dto.FileVO;
import com.kosta.board.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardDAO boardDAO;

    @Override
    public List<Board> boardListByPage(PageInfo pageInfo) throws Exception {
        int boardCount = boardDAO.selectBoardCount();
        if (boardCount==0) return null; // 게시글이 없을 때 null 리턴

        // 페이징 처리하기
        int allPage = (int) Math.ceil((double)boardCount/10);
        int startPage = (pageInfo.getCurPage()-1)/10*10+1;
        int endPage = Math.min(startPage+10-1, allPage);
        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);
        if(pageInfo.getCurPage()>allPage) pageInfo.setCurPage(allPage);

        // 조회할 게시글의 시작 행 계산
        int row = (pageInfo.getCurPage()-1)*10+1;

        return boardDAO.selectBoardList(row-1);
    }

    @Override
    public Board boardDetail(Integer num) throws Exception {
        Board board = boardDAO.selectBoard(num);
        if (board==null) throw new Exception("글 번호 오류");
        return board;
    }

    @Override
    public void readImage(Integer num, OutputStream out) throws Exception {
        String dir = "d:/seonjin/upload/";
        FileInputStream fis = new FileInputStream(dir+num);
        FileCopyUtils.copy(fis, out);
        fis.close();
    }

    @Override
    public void boardWrite(Board board, MultipartFile file) throws Exception {
        if(file!=null && !file.isEmpty()) {
            String dir = "D:/seonjin/upload/";
            FileVO fileVO = new FileVO();
            fileVO.setDirectory(dir);
            fileVO.setName(file.getOriginalFilename());
            fileVO.setSize(file.getSize());
            fileVO.setContenttype(file.getContentType());
            fileVO.setData(file.getBytes());
            boardDAO.insertFile(fileVO);

            Integer num = fileVO.getNum();

            File uploadFile = new File(dir+fileVO.getNum());
            file.transferTo(uploadFile);
            board.setFileurl(fileVO.getNum()+"");
        }
        boardDAO.insertBoard(board);
    }

    @Override
    public Board boardModify(Board board, MultipartFile file) throws Exception { // 생성 때와 유사한 로직
        // file이 존재할 때
        if(file!=null && !file.isEmpty()) {
            // 1. 파일정보 DB에 추가
            String dir = "D:/seonjin/upload/";
            FileVO fileVO = new FileVO();
            fileVO.setDirectory(dir);
            fileVO.setName(file.getOriginalFilename());
            fileVO.setSize(file.getSize());
            fileVO.setContenttype(file.getContentType());
            fileVO.setData(file.getBytes());
            boardDAO.insertFile(fileVO);

            // 2. upload 폴더에 파일 업로드
            File uploadFile = new File(dir+fileVO.getNum());
            file.transferTo(uploadFile);

            // 3. 기존 파일번호 삭제를 위해 받아놓기
            Integer deleteFileNum = null;
            if(board.getFileurl()!=null && board.getFileurl().trim().equals("")) {
                deleteFileNum = Integer.parseInt(board.getFileurl());
            }
            board.setFileurl(fileVO.getNum()+"");

            // 4. 파일번호를 board fileUrl에 복사 & board update
            boardDAO.updateBoard(board);

            // 5. board fileUrl에 해당하는 파일 번호를 파일 테이블에서 삭제
            if(deleteFileNum!=null) {
                boardDAO.deleteFile(deleteFileNum);
            }
        } else {
            boardDAO.updateBoard(board);
        }
        return boardDAO.selectBoard(board.getNum());
    }

    public void boardDelete(Integer num) throws Exception {
        Board board = boardDAO.selectBoard(num);
        if(board != null) {
            if(board.getFileurl()!=null && !board.getFileurl().equals("")) { // 파일이 있는 경우 파일 먼저 삭제
                boardDAO.deleteFile(Integer.parseInt(board.getFileurl()));
            }
        boardDAO.deleteBoard(num);
        }
    }
    @Override
    public List<Board> searchListByPage(String type, String keyword, PageInfo pageInfo) throws Exception {
        // 페이징 처리
        int searchCount = boardDAO.searchBoardCount(type, keyword);
        if(searchCount==0) return null;

        int allPage = (int)Math.ceil((double)searchCount/10);
        int startPage = (pageInfo.getCurPage()-1)/10*10+1;
        int endPage = Math.min(startPage+10-1, allPage);

        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);
        if(pageInfo.getCurPage()>allPage) pageInfo.setCurPage(allPage);

        int row = (pageInfo.getCurPage()-1)*10+1;

        return boardDAO.searchBoardList(type, keyword, row-1);
    }
}

