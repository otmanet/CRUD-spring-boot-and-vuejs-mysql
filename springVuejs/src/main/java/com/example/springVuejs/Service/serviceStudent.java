package com.example.springVuejs.Service;

import java.util.List;

import com.example.springVuejs.Repository.studentRepository;
import com.example.springVuejs.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class serviceStudent {

    @Autowired
    private studentRepository studentrepository;

    public List<Student> findAll() {
        return studentrepository.findAll();
    }

    public void save(Student st) {
        studentrepository.save(st);
    }

    public Student getById(Long id) {
        return studentrepository.findById(id).get();
    }

    public void delete(Long id) {
        studentrepository.deleteById(id);
    }
}
