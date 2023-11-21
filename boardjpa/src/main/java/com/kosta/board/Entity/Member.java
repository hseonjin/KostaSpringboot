package com.kosta.board.Entity;

import com.kosta.board.dto.MemberDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String address;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Board> boardList = new ArrayList<>();
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Boardlike> boardLike = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("[%s,%s,%s,%s,%s]", id,name,password,email,address);
    }

    public MemberDTO toDTO() {
        return MemberDTO.builder()
                .id(id)
                .name(name)
                .password(password)
                .email(email)
                .address(address)
                .build();
    }

}
