package com.kosta.board.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Boardlike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="boardNum")
    private Board board;

    @Override
    public String toString() {
        return String.format("[%d,%s,%d]", num, member.getId(), board.getNum());

    }
}
