package com.kosta.board.service;

import com.kosta.board.dao.BoardDAO;
import com.kosta.board.dao.BoardLikeDAO;
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
    @Autowired
    private BoardLikeDAO boardLikeDAO;

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
    public Integer boardWrite(Board board, List<MultipartFile> files) throws Exception {
        String dir = "D:/seonjin/upload/";
        if(files!=null && files.size()!=0) {
            String fileNums = "";
            for(MultipartFile file : files) {
                // file 테이블에 insert
                FileVO fileVO = new FileVO();
                fileVO.setDirectory(dir);
                fileVO.setName(file.getOriginalFilename());
                fileVO.setSize(file.getSize());
                fileVO.setContenttype(file.getContentType());
                fileVO.setData(file.getBytes());
                boardDAO.insertFile(fileVO);

                // upload 폴더에 업로드
                File uploadFile = new File(dir+fileVO.getNum());
                file.transferTo(uploadFile);
                // file 번호 목록 만들기
                if(!fileNums.equals(""))
                    fileNums += ",";
                fileNums += fileVO.getNum();
            }
            board.setFileurl(fileNums); // 1,2,3
        }
        boardDAO.insertBoard(board);
        return board.getNum();
    }

    @Override
    public Integer boardModify(Board board, List<MultipartFile> fileList) throws Exception { // 생성 때와 유사한 로직
        if(fileList!=null && fileList.size()!=0) {
            String dir = "D:/seonjin/upload/";
            String fileNums = "";
            for(MultipartFile file : fileList) {
                if (file.isEmpty()) {
                    fileNums += (fileNums.equals("") ? "" : ",")+file.getOriginalFilename();
                } else {
                    // file 테이블에 insert
                    FileVO fileVO = new FileVO();
                    fileVO.setDirectory(dir);
                    fileVO.setName(file.getOriginalFilename());
                    fileVO.setSize(file.getSize());
                    fileVO.setContenttype(file.getContentType());
                    fileVO.setData(file.getBytes());
                    boardDAO.insertFile(fileVO);

                    // upload 폴더에 업로드
                    File uploadFile = new File(dir + fileVO.getNum());
                    file.transferTo(uploadFile);
                    fileNums += (fileNums.equals("") ? "" : ",") + fileVO.getNum();
                }
            }
            board.setFileurl(fileNums); // 1,2,3
        } else {
            board.setFileurl(null);
        }
        boardDAO.updateBoard(board);
        return board.getNum();
    }

    private void fileDelete(Integer num) throws Exception {
        Board board = boardDAO.selectBoard(num);
        if(board==null) throw new Exception("글 번호 오류");
        String fileUrl = board.getFileurl();

        if(fileUrl!=null && !fileUrl.equals("")) { // 파일이 있는 경우 파일 먼저 삭제
            String[] fileList = fileUrl.split(",");
            for (String fileNum : fileList) {
                boardDAO.deleteFile(Integer.valueOf(fileNum));
                String dir = "d:/seonjin/upload/";
                File file = new File(dir + fileNum);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    public void boardDelete(Integer num) throws Exception {
        fileDelete(num);
        boardDAO.deleteBoard(num);
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

    @Override
    public Boolean isHeartBoard(String id, Integer num) throws Exception {
        Integer heartNum = boardLikeDAO.selectBoardLike(id, num);
        return heartNum == null ? false :true;
    }

    @Override
    public void selHeartBoard(String id, Integer num) throws Exception {
        boardLikeDAO.insertBoardLike(id, num);
    }

    @Override
    public void delHeartBoard(String id, Integer num) throws Exception {
        boardLikeDAO.deleteBoardLike(id, num);
    }
}

