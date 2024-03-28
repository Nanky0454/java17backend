package org.proyectofinal.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.proyectofinal.dto.CourseDTO;
import org.proyectofinal.dto.GenericResponse;
import org.proyectofinal.dto.RegistrationDTO;
import org.proyectofinal.dto.RegistrationDetailDTO;
import org.proyectofinal.model.Registration;
import org.proyectofinal.service.impl.RegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/registrations")
@RequiredArgsConstructor
public class RegistrationController {
    //@Autowired
    private final RegistrationServiceImpl service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<RegistrationDTO>> readAll() throws Exception{
        //ModelMapper modelMapper = new ModelMapper();
        List<RegistrationDTO> list = service.readAll().stream().map(this::convertToDto).toList();
        //List<RegistrationDTO> list = service.readAll().stream().map(e-> new RegistrationDTO(e.getIdRegistration(),e.getName(),e.getLastName(),e.getDni(),e.getAge())).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/relations")
    public ResponseEntity<Map<String, List<String>>> getCoursesAndStudents() throws Exception{
        Map<String, List<String>> coursesAndStudents = new HashMap<>();

        List<RegistrationDTO> registrations= service.readAll().stream().map(this::convertToDto).toList();

        for(RegistrationDTO registration : registrations){
            List<RegistrationDetailDTO> details = registration.getDetails();
            for(RegistrationDetailDTO detail : details){
                CourseDTO course = detail.getCourse();
                String courseName = course.getNameCourse();

                String fullName = registration.getStudent().getNameStudent() + " " + registration.getStudent().getLastName();

                coursesAndStudents.computeIfAbsent(courseName, k -> new ArrayList<>()).add(fullName);
            }
        }

        return ResponseEntity.ok(coursesAndStudents);
    }
    /*@GetMapping("/{id}")
    public ResponseEntity<RegistrationDTO> radById(@PathVariable("id") Integer id) throws Exception{
        Registration obj = service.readById(id);
        return ResponseEntity.ok(convertToDto(obj));
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<RegistrationDTO>> radById(@PathVariable("id") Integer id) throws Exception{
        Registration obj = service.readById(id);
        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList(convertToDto(obj))));
    }

    @PostMapping
    public ResponseEntity<RegistrationDTO> save(@Valid @RequestBody RegistrationDTO dto) throws Exception{
        Registration obj = service.save(convertToEntity(dto));
        return new ResponseEntity <>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<RegistrationDTO> update(@Valid @RequestBody RegistrationDTO dto, @PathVariable("id") Integer id) throws Exception{
        Registration obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    ///////////////////////////////////////////
    private RegistrationDTO convertToDto(Registration obj){
        return modelMapper.map(obj, RegistrationDTO.class);
    }

    private Registration convertToEntity(RegistrationDTO dto){
        return modelMapper.map(dto, Registration.class);
    }
}
