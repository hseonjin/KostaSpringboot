package com.kosta.univ.repository;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByName(String name);
    List<Student> findByGrade(Integer grade);
    List<Student> findByProfIsNull();
    Optional<Student> findByJumin(String jumin);
}
