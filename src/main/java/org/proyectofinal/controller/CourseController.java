package org.proyectofinal.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.proyectofinal.dto.GenericResponse;
import org.proyectofinal.dto.CourseDTO;
import org.proyectofinal.model.Course;
import org.proyectofinal.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    //@Autowired
    private final CourseServiceImpl service;
    @Qualifier("courseMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> readAll() throws Exception{
        //ModelMapper modelMapper = new ModelMapper();
        List<CourseDTO> list = service.readAll().stream().map(this::convertToDto).toList();
        //List<CourseDTO> list = service.readAll().stream().map(e-> new CourseDTO(e.getIdCourse(),e.getName(),e.getLastName(),e.getDni(),e.getAge())).toList();
        return ResponseEntity.ok(list);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<CourseDTO> radById(@PathVariable("id") Integer id) throws Exception{
        Course obj = service.readById(id);
        return ResponseEntity.ok(convertToDto(obj));
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<CourseDTO>> radById(@PathVariable("id") Integer id) throws Exception{
        Course obj = service.readById(id);
        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList(convertToDto(obj))));
    }

    @PostMapping
    public ResponseEntity<CourseDTO> save(@Valid @RequestBody CourseDTO dto) throws Exception{
        Course obj = service.save(convertToEntity(dto));
        return new ResponseEntity <>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@Valid @RequestBody CourseDTO dto, @PathVariable("id") Integer id) throws Exception{
        Course obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    ///////////////////////////////////////////
    private CourseDTO convertToDto(Course obj){
        return modelMapper.map(obj, CourseDTO.class);
    }

    private Course convertToEntity(CourseDTO dto){
        return modelMapper.map(dto, Course.class);
    }
}
