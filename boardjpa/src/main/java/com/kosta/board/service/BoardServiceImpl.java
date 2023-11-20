package com.kosta.board.service;

import com.kosta.board.Entity.Board;
import com.kosta.board.dto.BoardDTO;
import com.kosta.board.Entity.FileVO;
import com.kosta.board.repository.BoardRepository;
import com.kosta.board.repository.FileVORepository;
import com.kosta.board.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private FileVORepository fileVORepository;
    @Override
    public List<BoardDTO> boardListByPage(PageInfo pageInfo) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageInfo.getCurPage()-1, 10, Sort.by(Sort.Direction.DESC, "num"));
        Page<Board> pages = boardRepository.findAll(pageRequest);
        pageInfo.setAllPage(pages.getTotalPages());
        int startPage = (pageInfo.getCurPage()-1)/10*10+1;
        int endPage = Math.min(startPage+10-1, pageInfo.getAllPage());
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (Board board : pages.getContent()) {
            boardDTOList.add(board.toDTO());
        }
        return boardDTOList;
    }

    @Override
    public BoardDTO boardDetail(Integer num) throws Exception {
        Optional<Board> oboard = boardRepository.findById(num);
        if(oboard.isEmpty()) throw new Exception("글 번호 오류");
        return oboard.get().toDTO(); // toDTO에서 null일 때, nullPointException 발생->방지를 위해 예외처리
    }

    @Override
    public void readImage(Integer num, OutputStream out) throws Exception {
        String dir = "d:/seonjin/upload/";
        FileInputStream fis = new FileInputStream(dir+num);
        FileCopyUtils.copy(fis, out);
        fis.close();
    }

    @Override
    public Integer boardWrite(BoardDTO boardDto, List<MultipartFile> files) throws Exception {
        if(files!=null && files.size()!=0) {
            String dir = "D:/seonjin/upload/";
            String fileNums = "";
            for(MultipartFile file : files) {
                // file 테이블에 insert
//                FileVO fileVO = new FileVO();
//                fileVO.setDirectory(dir);
//                fileVO.setName(file.getOriginalFilename());
//                fileVO.setSize(file.getSize());
//                fileVO.setContenttype(file.getContentType());
//                fileVO.setData(file.getBytes());
                FileVO fileVO = FileVO.builder()
                        .directory(dir)
                        .name(file.getOriginalFilename())
                        .size(file.getSize())
                        .contenttype(file.getContentType())
                        .data(file.getBytes())
                        .build();
                fileVORepository.save(fileVO);

                // upload 폴더에 업로드
                File uploadFile = new File(dir+fileVO.getNum());
                file.transferTo(uploadFile);
                // file 번호 목록 만들기
                if(!fileNums.equals(""))
                    fileNums += ",";
                fileNums += fileVO.getNum();
            }
            boardDto.setFileurl(fileNums); // 1,2,3
        }
        Board board = boardDto.toEntity();
        boardRepository.save(board);
        return board.getNum();
    }

    @Override
    public Integer boardModify(BoardDTO boardDTO, List<MultipartFile> files) throws Exception {
        Board board = boardRepository.findById(boardDTO.getNum()).get();
        board.setContent(boardDTO.getContent());
        board.setSubject(boardDTO.getSubject());
        if(files!=null && files.size()!=0) {
            String dir = "D:/seonjin/upload/";
            String fileNums = "";
            for(MultipartFile file : files) {
                if (file.isEmpty()) {
                    fileNums += (fileNums.equals("") ? "" : ",")+file.getOriginalFilename();
                } else {
                    // file 테이블에 insert
                    FileVO fileVO = FileVO.builder()
                            .directory(dir)
                            .name(file.getOriginalFilename())
                            .size(file.getSize())
                            .contenttype(file.getContentType())
                            .data(file.getBytes())
                            .build();
                    fileVORepository.save(fileVO);

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
        boardRepository.save(board);
        return board.getNum();
    }

    @Override
    public void boardDelete(Integer num) throws Exception {
        Board board = boardRepository.findById(num).get();
        if(board==null) throw new Exception("글 번호 오류");
        String fileUrl = board.getFileurl();

        if (fileUrl != null && !fileUrl.equals("")) {
            String[] fileList = fileUrl.split(",");
            for (String fileNum : fileList) {
                Optional<Board> boardOptional = boardRepository.findById(Integer.valueOf(fileNum));
                if (boardOptional.isPresent()) {
                    boardRepository.deleteById(Integer.valueOf(fileNum));
                    String dir = "d:/seonjin/upload/";
                    File file = new File(dir + fileNum);
                    if (file.exists()) {
                        file.delete();
                    }
                }
            }
        }
        boardRepository.deleteById(num);
    }

    @Override
    public List<BoardDTO> searchListByPage(String type, String keyword, PageInfo pageInfo) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageInfo.getCurPage()-1, 10,
                Sort.by(Sort.Direction.DESC, "num"));
        Page<Board> pages = null;
        if(type.equals("subject")) {
            pages = boardRepository.findBySubjectContains(keyword, pageRequest);
        } else if(type.equals("content")) {
            pages = boardRepository.findByContentContains(keyword, pageRequest);
        } else if (type.equals("writer")) {
//            pages = boardRepository.findByWriterContains(keyword, pageRequest);
        } else {
            return null;
        }
        pageInfo.setAllPage(pages.getTotalPages());
        int startPage = (pageInfo.getCurPage()-1)/10*10+1;
        int endPage = Math.min(startPage+10-1, pageInfo.getAllPage());
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (Board board : pages.getContent()) {
            boardDTOList.add(board.toDTO());
        }
        return boardDTOList;
    }

    @Override
    public Boolean isHeartBoard(String id, Integer num) throws Exception {
        return null;
    }

    @Override
    public void selHeartBoard(String id, Integer num) throws Exception {

    }

    @Override
    public void delHeartBoard(String id, Integer num) throws Exception {

    }
    @Override
    public void plusViewCount(Integer num) throws Exception {
        Board board = boardRepository.findById(num).get();
        board.setViewcount(board.getViewcount()+1);
        boardRepository.save(board);
    }
}

