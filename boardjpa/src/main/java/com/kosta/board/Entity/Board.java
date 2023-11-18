package com.kosta.board.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @ManyToOne(fetch = FetchType.EAGER) // 다대일 관계, Eager 패치 방식
    @JoinColumn(name="writer")
    private Member member;

    @Override
    public String toString() {
        return String.format("[%d,%s,%s,%s,%s]", num, subject, content, fileurl, member.getId());
    }
}
