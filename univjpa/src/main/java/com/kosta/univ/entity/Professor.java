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
@ToString(exclude = {"studList", "dept"})
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
    private String email;
    @Column
    private String hpage;
    @OneToMany(mappedBy = "prof", fetch = FetchType.LAZY)
    private List<Student> studList = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deptno")
    private Department dept;
}
