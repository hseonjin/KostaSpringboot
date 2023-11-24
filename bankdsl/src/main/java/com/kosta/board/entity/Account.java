package com.kosta.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private Integer balance;
    @Column
    private String type;
    @Column
    private String grade;

    public void deposit(Integer money) {
        if(money>0) {
            this.balance += money;
        }
    }
    public void withdraw(Integer money) throws Exception {
        if(balance<money) throw new Exception("잔액 부족");
        balance -= money;
    }
}
