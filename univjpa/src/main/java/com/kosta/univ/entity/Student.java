package com.kosta.univ.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"dept1", "dept2", "prof"})
public class Student {
    @Id
    private Integer studno;
    @Column
    private String name;
    @Column
    private String id;
    @Column
    private Integer grade;
    @Column
    private String jumin;
    @Column
    private Date birthday;
    @Column
    private String tel;
    @Column
    private Integer height;
    @Column
    private Integer weight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deptno1") // student 테이블의 deptno1과 매핑
    private Department dept1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deptno2")
    private Department dept2;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profno")
    private Professor prof;
}
