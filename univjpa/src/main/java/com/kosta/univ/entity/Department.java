package com.kosta.univ.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//@Data // @Getter, @Setter, @ToString, @RequiredArgsConstructor 포함
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    private Integer deptno;
    @Column
    private String dname;
    @Column
    private Integer part;
    @Column
    private String build;

    @Override
    public String toString() {
        return String.format("[%d,%s,%d,%s]", deptno,dname, part, build);
    }
}
