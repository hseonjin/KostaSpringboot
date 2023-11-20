package com.kosta.board.dto;

import com.kosta.board.Entity.Board;
import com.kosta.board.Entity.Member;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
	private Integer num;
	private String subject;
	private String content;
	private Date writedate;
	private String fileurl;
	private String writer;
	private Integer viewcount;
	private Integer likecount;
	private String writername;
	
	public Board toEntity() {
		return Board.builder()
				.num(num) // 엔터티의 속성(dto의 파라미터)
				.subject(subject)
				.content(content)
				.fileurl(fileurl)
				.member(Member.builder().id(writer).build())
				.viewcount(viewcount)
				.likecount(likecount)
				.writedate(writedate)
				.build();
	}

}
