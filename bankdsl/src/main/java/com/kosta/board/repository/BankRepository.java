package com.kosta.board.repository;

import com.kosta.board.entity.Account;
import com.kosta.board.entity.QAccount;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BankRepository {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    EntityManager entityManager;

    // 특정 계좌 조회
    public Account findAccountById(String id) {
        QAccount acc = QAccount.account;
        return jpaQueryFactory.select(acc).from(acc).where(acc.id.eq(id)).fetchOne();
    }

    // 전체 계좌 조회
    public List<Account> findAllAccount() {
        QAccount acc = QAccount.account;
        return jpaQueryFactory.select(acc).from(acc).fetch();
    }

    // 입출금
    public void updateBalance(String id, Integer balance) {
        QAccount acc = QAccount.account;
        jpaQueryFactory.update(acc)
                .set(acc.balance, balance)
                .where(acc.id.eq(id))
                .execute();
        entityManager.flush();
        entityManager.clear();
    }
}
