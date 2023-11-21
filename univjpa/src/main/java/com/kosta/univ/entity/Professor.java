package com.kosta.univ.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
    @Id
    private Integer profno;
    @Column
    private String name;
    @Column
    private String id;
    @Column
    private String position;
    @Column
    private Integer pay;
    @Column
    private Date hiredate;
    @Column
    private Integer bonus;
    @Column
    private Integer deptno;
    @Column
    private String email;
    @Column
    private String hpage;

    @OneToMany(mappedBy = "prof", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Student> studentList = new ArrayList<>();

//    @Override
//    public String toString() {
//        return String.format("[%d,%s,%s,%s,%d,]", deptno,dname, part, build);
//    }
}
