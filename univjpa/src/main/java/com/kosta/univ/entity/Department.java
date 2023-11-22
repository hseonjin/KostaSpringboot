package com.kosta.univ.entity;

import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"studList1", "studList2", "profList"})
public class Department {
    @Id
    private Integer deptno;
    @Column
    private String dname;
    @Column
    private Integer part;
    @Column
    private String build;
    @OneToMany(mappedBy = "dept1", fetch = FetchType.LAZY)
    private List<Student> studList1= new ArrayList<>();
    @OneToMany(mappedBy = "dept2", fetch = FetchType.LAZY)
    private List<Student> studList2= new ArrayList<>();
    @OneToMany(mappedBy = "dept", fetch = FetchType.LAZY)
    private List<Professor> profList = new ArrayList<>();
}
