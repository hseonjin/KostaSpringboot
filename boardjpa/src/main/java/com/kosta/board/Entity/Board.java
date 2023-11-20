package com.kosta.board.Entity;

import com.kosta.board.dto.BoardDTO;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가하는 값을 위해 설정
    private Integer num;
    @Column
    private String subject;
    @Column
    private String content;
    @Column
    private String fileurl;
    @Column
    @ColumnDefault("0")
    private Integer viewcount;
    @Column
    @ColumnDefault("0")
    private Integer likecount;
    @Column
    @CreationTimestamp
    private Date writedate;

    @ManyToOne(fetch = FetchType.EAGER) // 다대일 관계, Eager 패치 방식
    @JoinColumn(name="writer")
    private Member member;

    @Override
    public String toString() {
        return String.format("[%d,%s,%s,%s,%s]", num, subject, content, fileurl, member.getId());
    }

    public BoardDTO toDTO() {
        return BoardDTO.builder()
                .num(num)
                .subject(subject)
                .content(content)
                .fileurl(fileurl)
                .writer(member.getId())
                .writername(member.getName())
                .viewcount(viewcount)
                .likecount(likecount)
                .writedate(writedate)
                .build();
    }
}
