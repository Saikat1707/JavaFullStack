package com.student;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class StudentService {
	private StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void save(Student student) {
        studentDao.save(student);
    }

    public Student findById(int id) {
        return studentDao.findById(id);
    }

    public List<Student> getAll() {
        return studentDao.getAll();
    }

    public void update(Student student) {
        studentDao.update(student);
    }

    public void delete(int id) {
        studentDao.delete(id);
    }
}
