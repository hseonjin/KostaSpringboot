package com.kosta.univ.repository;

import com.kosta.univ.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository repository;

    @Test // 학생 이름으로 학생 목록 조회
    void studentListByNameTest() {
        Student stu = repository.findById()
    }

}