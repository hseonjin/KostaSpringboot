package com.kosta.univ.repository;

import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
