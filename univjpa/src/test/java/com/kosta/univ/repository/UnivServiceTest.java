package com.kosta.univ.repository;

import com.kosta.univ.entity.Department;
import com.kosta.univ.service.UnivServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
class UnivServiceTest {
    @Autowired
    private StudentRepository repository;
    @Autowired
    private DepartmentRepository dRepository;
    @Autowired
    private ProfessorRepository pRepository;
    @Autowired
    private UnivServiceImpl service;

    @Test // 학생 이름으로 학생 목록 조회
    void studByNameTest() {
//        System.out.println(repository.findByName("서진수"));
        try {
            System.out.println(service.studentListByName("서진수"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test // 제1전공으로/제2전공으로 학생 목록 조회
    @Transactional
    void studByDeptno1Test() {
//        Optional<Department> odept = dRepository.findByDname("컴퓨터공학과");
//        System.out.println(repository.findByDept1_deptno(odept.get().getDeptno()));
        try {
            Optional<Department> odept = dRepository.findByDname("전자공학과");
            System.out.println(new ArrayList<>(odept.get().getStudList2()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test // 학년으로 학생 목록 조회
    void studGradeTest() {
        System.out.println(repository.findByGrade(4));
    }
    @Test // 담당교수 없는 학생 목록 조회
    void studNoProfTest() {
        System.out.println(repository.findByProfIsNull());
    }
    @Test // 학번으로 학생 조회
    void studNoTest() {
        try {
            System.out.println(service.studentByStudno(9411));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test // 주민번호로 학생 조회
    void studJuminTest() {
        try {
            System.out.println(service.studentByJumin("7802232116780"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test // 교수번호로 담당 학생 목록 조회
    void studProfNoTest() {
        try {
            System.out.println(service.studentListByProfNo(1002));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test // 교수이름으로 담당 학생 목록 조회
    void studProfNameTest() {
        try {
            System.out.println(service.studentListByProfName("양선희"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test // 교수번호로 교수 조회
    void profNoTest() {
        try {
            System.out.println(service.professorByProfNo(1001));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test // 교수이름으로 교수 조회
    void profNameTest() {
        try {
            System.out.println(service.professorByProfName("양선희"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test // 학과 번호로 교수 조회
    void profDeptNoTest() {
        try {
            System.out.println(service.professorListByDeptNo(101));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test // 학과 이름으로 교수 조회
    void profDeptNameTest() {
        try {
            System.out.println(service.professorListByDeptName("컴퓨터공학과"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test // 직급으로 교수 조회
    void profPositionTest() {
        try {
            System.out.println(service.professorListByPosition("정교수"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test // 학과번호로 학과 조회
    void deptNoTest() {
        try {
            System.out.println(service.departmentByDeptNo(10));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test // 학과이름으로 학과 조회
    void deptNameTest() {
        try {
            System.out.println(service.departmentByDeptName("컴퓨터공학과"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test // 학부로 학과 조회
    void deptPartTest() {
        try {
            System.out.println(service.departmentListByPart(10));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}