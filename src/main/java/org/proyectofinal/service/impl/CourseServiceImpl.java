package org.proyectofinal.service.impl;

import lombok.RequiredArgsConstructor;
import org.proyectofinal.model.Course;
import org.proyectofinal.repo.ICourseRepo;
import org.proyectofinal.repo.IGenericRepo;
import org.proyectofinal.service.ICourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends CRUDImpl<Course, Integer> implements ICourseService {
    //@Autowired
    private final ICourseRepo repo;

    @Override
    protected IGenericRepo<Course, Integer> getRepo() {
        return repo;
    }
}
