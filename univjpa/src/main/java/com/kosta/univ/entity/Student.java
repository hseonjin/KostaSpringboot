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
    @Column
    private Integer deptno1;
    @Column
    private Integer deptno2;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="profno")
    private Professor prof;
}
