package com.karan.demo.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getStudent(){
        return repository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = repository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        else{
            repository.save(student);
        }
    }

    public void deleteStudent(Long studentId) {
       boolean exists = repository.existsById(studentId);
       if(!exists){
           throw new IllegalStateException("student with id " + studentId + "does not exist");
       }
       repository.deleteById(studentId);
    }
}
