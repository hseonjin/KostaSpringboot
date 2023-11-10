package com.kosta.board.dao;

import com.kosta.board.dto.Board;
import com.kosta.board.dto.FileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardDAO {
    void insertBoard(Board board) throws Exception; // 게시글 작성
    List<Board> selectBoardList(Integer row) throws Exception; // 게시글 리스트
    Integer selectBoardCount() throws Exception; // 게시글 개수
    Board selectBoard(Integer num) throws Exception; // 게시글 상세
    void updateBoard(Board board) throws Exception; // 게시글 수정
    void deleteBoard(Integer num) throws Exception; // 게시글 삭제
    List<Board> searchBoardList(@Param("type") String type,
                                @Param("keyword") String keyword,
                                @Param("row") Integer row)
            throws Exception; // 검색한 게시글 리스트
    Integer searchBoardCount(@Param("type") String type,
                             @Param("keyword") String keyword)
            throws Exception; // 검색한 게시글 개수
    void updateBoardViewCount(Integer num) throws Exception; // 조회수
    Integer selectLikeCount(Integer num) throws Exception; // 좋아요수
    void plusBoardLikeCount(Integer num) throws Exception; // 좋아요수 +
    void minusBoardLikeCount(Integer num) throws Exception; // 좋아요수 -

    void insertFile(FileVO fileVO) throws Exception; // 파일 삽입
    FileVO selectFile(Integer num) throws Exception; // 파일 선택
    void deleteFile(Integer num) throws Exception; // 파일 삭제
}
