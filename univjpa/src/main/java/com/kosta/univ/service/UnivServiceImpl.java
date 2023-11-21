package com.kosta.univ.service;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.Student;
import com.kosta.univ.repository.DepartmentRepository;
import com.kosta.univ.repository.ProfessorRepository;
import com.kosta.univ.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UnivServiceImpl implements UnivService{
    @Autowired
    private StudentRepository sRepository;
    @Autowired
    private DepartmentRepository dRepository;
    @Autowired
    private ProfessorRepository pRepository;

    @Override
    public List<Student> studentListByName(String studName) throws Exception { // 학생 이름으로 학생 목록 조회
        return null;
    }

    @Override
    public List<Student> studentListInDept1ByDeptName(String deptName) throws Exception {
        return null;
    }

    @Override
    public List<Student> studentListInDept2ByDeptName(String deptName) throws Exception {
        return null;
    }

    @Override
    public List<Student> studentListByGrade(Integer grade) throws Exception {
        return null;
    }

    @Override
    public List<Student> studentListByNoProfessor() throws Exception {
        return null;
    }

    @Override
    public Student studentByStudno(Integer studno) throws Exception {
        return null;
    }

    @Override
    public Student studentByJumin(String jumin) throws Exception {
        return null;
    }

    @Override
    public List<Student> studentListByProfNo(Integer profNo) throws Exception {
        return null;
    }

    @Override
    public List<Student> studentListByProfName(String profName) throws Exception {
        return null;
    }

    @Override
    public Professor professorByProfNo(Integer profNo) throws Exception {
        return null;
    }

    @Override
    public List<Professor> professorListByProfName(String profName) throws Exception {
        return null;
    }

    @Override
    public List<Professor> professorListByDeptNo(Integer deptNo) throws Exception {
        return null;
    }

    @Override
    public List<Professor> professorListByDeptName(String deptName) throws Exception {
        return null;
    }

    @Override
    public List<Professor> professorListByPosition(String position) throws Exception {
        return null;
    }

    @Override
    public Department departmentByDeptNo(Integer deptNo) throws Exception {
        return null;
    }

    @Override
    public Department departmentByDeptName(String deptName) throws Exception {
        return null;
    }

    @Override
    public List<Department> departmentListByPart(Integer part) throws Exception {
        return null;
    }
}
