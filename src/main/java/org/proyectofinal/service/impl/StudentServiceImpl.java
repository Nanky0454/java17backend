package org.proyectofinal.service.impl;

import lombok.RequiredArgsConstructor;
import org.proyectofinal.model.Student;
import org.proyectofinal.repo.IGenericRepo;
import org.proyectofinal.repo.IStudentRepo;
import org.proyectofinal.repo.StudentRepo;
import org.proyectofinal.service.IStudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends CRUDImpl<Student, Integer> implements IStudentService {
    //@Autowired
    private final IStudentRepo repo;

    @Override
    protected IGenericRepo<Student, Integer> getRepo() {
        return repo;
    }


}
