package org.proyectofinal.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.proyectofinal.dto.GenericResponse;
import org.proyectofinal.dto.StudentDTO;
import org.proyectofinal.model.Student;
import org.proyectofinal.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    //@Autowired
    private final StudentServiceImpl service;
    @Qualifier("studentMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> readAll() throws Exception{
        //ModelMapper modelMapper = new ModelMapper();
        List<StudentDTO> list = service.readAll()
                .stream()
                .map(this::convertToDto)
                .toList();
        //List<StudentDTO> list = service.readAll().stream().map(e-> new StudentDTO(e.getIdStudent(),e.getName(),e.getLastName(),e.getDni(),e.getAge())).toList();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/order")
    public ResponseEntity<List<StudentDTO>> orderByAge() throws Exception{
        //ModelMapper modelMapper = new ModelMapper();
        List<StudentDTO> list = service.readAll()
                .stream()
                .map(this::convertToDto)
                .sorted(Comparator.comparing(StudentDTO::getAge).reversed())
                .collect(Collectors.toList());
        //List<StudentDTO> list = service.readAll().stream().map(e-> new StudentDTO(e.getIdStudent(),e.getName(),e.getLastName(),e.getDni(),e.getAge())).toList();
        return ResponseEntity.ok(list);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<StudentDTO> radById(@PathVariable("id") Integer id) throws Exception{
        Student obj = service.readById(id);
        return ResponseEntity.ok(convertToDto(obj));
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<StudentDTO>> radById(@PathVariable("id") Integer id) throws Exception{
        Student obj = service.readById(id);
        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList(convertToDto(obj))));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> save(@Valid @RequestBody StudentDTO dto) throws Exception{
        Student obj = service.save(convertToEntity(dto));
        return new ResponseEntity <>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@Valid @RequestBody StudentDTO dto, @PathVariable("id") Integer id) throws Exception{
        Student obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    ///////////////////////////////////////////
    private StudentDTO convertToDto(Student obj){
        return modelMapper.map(obj, StudentDTO.class);
    }

    private Student convertToEntity(StudentDTO dto){
        return modelMapper.map(dto, Student.class);
    }
}
