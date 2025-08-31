package com.karan.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
            Student student = repository.findById(studentId).orElseThrow(()->new IllegalStateException(
               "student with id " + studentId + "does not exist"
            ));
            if (name!=null && !name.isEmpty() && !Objects.equals(student.getName(),name)){
                student.setName(name);
            }
        if (email!=null && !email.isEmpty() && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = repository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
