package org.proyectofinal.repo;


import org.proyectofinal.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepo {
    public Student getStudentX(Student student){
        return student;
    }
}
