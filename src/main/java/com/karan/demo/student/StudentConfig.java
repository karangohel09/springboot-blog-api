package com.karan.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
             Student karan = new Student(
                     "karan",
                     "karangohel2093@gmail.com",
                     LocalDate.of(2003, Month.SEPTEMBER,20)
             );
            Student deep = new Student(
                    "deep",
                    "dshreyaskar@gmail.com",
                    LocalDate.of(2004, Month.APRIL,19)
            );
            repository.saveAll(
                    List.of(karan,deep)
            );
        };
    }
}
