package com.kosta.univ.service;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.Student;
import com.kosta.univ.repository.DepartmentRepository;
import com.kosta.univ.repository.ProfessorRepository;
import com.kosta.univ.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnivServiceImpl implements UnivService {
    @Autowired
    private DepartmentRepository deptRepository;
    @Autowired
    private ProfessorRepository profRepository;
    @Autowired
    private StudentRepository studRepository;

    @Override
    public List<Student> studentListByName(String studName) throws Exception {
        return studRepository.findByName(studName);
    }

    @Override
    @Transactional
    public List<Student> studentListInDept1ByDeptName(String deptName) throws Exception {
        Optional<Department> odept = deptRepository.findByDname(deptName);
        if(odept.isEmpty()) throw new Exception("학과명 오류");
        return new ArrayList<>(odept.get().getStudList1());
    }

    @Override
    @Transactional
    public List<Student> studentListInDept2ByDeptName(String deptName) throws Exception {
        Optional<Department> odept = deptRepository.findByDname(deptName);
        if(odept.isEmpty()) throw new Exception("학과명 오류");
        return new ArrayList<>(odept.get().getStudList2());
    }

    @Override
    public List<Student> studentListByGrade(Integer grade) throws Exception {
        return studRepository.findByGrade(grade);
    }

    @Override
    public List<Student> studentListByNoProfessor() throws Exception {
        return studRepository.findByProfIsNull();
    }

    @Override
    public Student studentByStudno(Integer studno) throws Exception {
        Optional<Student> stud = studRepository.findById(studno);
        if(stud.isEmpty()) throw new Exception("학번 오류");
        return stud.get();
    }

    @Override
    public Student studentByJumin(String jumin) throws Exception {
        Optional<Student> stud = studRepository.findByJumin(jumin);
        if(stud.isEmpty()) throw new Exception("주민번호 오류");
        return stud.get();
    }

    @Override
    @Transactional
    public List<Student> studentListByProfNo(Integer profNo) throws Exception {
        Optional<Professor> prof = profRepository.findById(profNo);
        if(prof.isEmpty()) throw new Exception("교수번호 오류");
        return new ArrayList<>(prof.get().getStudList());
    }

    @Override
    @Transactional
    public List<Student> studentListByProfName(String profName) throws Exception {
        List<Professor> prof = profRepository.findByName(profName);
        List<Student> stud = new ArrayList<>();
        for(Professor p : prof) {
            stud.addAll(p.getStudList());
        }
        return stud;
    }

    @Override
    public Professor professorByProfNo(Integer profNo) throws Exception {
        Optional<Professor> prof = profRepository.findById(profNo);
        if(prof.isEmpty()) throw new Exception("교수번호 오류");
        return prof.get();
    }

    @Override
    public List<Professor> professorByProfName(String profName) throws Exception {
        return profRepository.findByName(profName);
    }

    @Override
    @Transactional
    public List<Professor> professorListByDeptNo(Integer deptNo) throws Exception {
        Optional<Department> dept = deptRepository.findById(deptNo);
        if(dept.isEmpty()) throw new Exception("학과번호 오류");
        return dept.get().getProfList();
    }

    @Override
    @Transactional
    public List<Professor> professorListByDeptName(String deptName) throws Exception {
        Optional<Department> dept = deptRepository.findByDname(deptName);
        if(dept.isEmpty()) throw new Exception("학과이름 오류");
        return dept.get().getProfList();
    }

    @Override
    public List<Professor> professorListByPosition(String position) throws Exception {
        return profRepository.findByPosition(position);
    }

    @Override
    public Department departmentByDeptNo(Integer deptNo) throws Exception {
        Optional<Department> dept = deptRepository.findById(deptNo);
        if(dept.isEmpty()) throw new Exception("학과번호 오류");
        return dept.get();
    }

    @Override
    public Department departmentByDeptName(String deptName) throws Exception {
        Optional<Department> dept = deptRepository.findByDname(deptName);
        if(dept.isEmpty()) throw new Exception("학과이름 오류");
        return dept.get();
    }

    @Override
    public List<Department> departmentListByPart(Integer part) throws Exception {
        return deptRepository.findByPart(part);
    }
}
