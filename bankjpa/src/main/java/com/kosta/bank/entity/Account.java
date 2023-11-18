package com.kosta.bank.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // 매칭할 테이블명과 클래스명이 다르게 할 경우 (name="")을 작성하여 맞춰주어야 한다
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
    @Id // PK가 되는 필드임을 명시
    private String id;
    @Column // 테이블의 column이 된다는 의미를 가짐
    private String name;
    @Column
    private Integer balance;
    @Column
    private String type;
    @Column
    private String grade;

    public void deposit(Integer money) {
        if(money>0) {
            balance += money;
        }
    }
    public void withdraw(Integer money) throws Exception {
        if(balance>=money) {
            balance -= money;
        } else {
            throw new Exception("잔액이 부족합니다");
        }
    }
}
