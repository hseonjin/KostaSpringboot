package com.kosta.board.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<Board> boardList = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("[%s,%s,%s,%s,%s]", id,name,password,email,address);
    }
}
