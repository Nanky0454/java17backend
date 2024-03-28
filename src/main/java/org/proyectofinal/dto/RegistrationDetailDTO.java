package org.proyectofinal.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.proyectofinal.model.Course;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationDetailDTO {

    @JsonBackReference
    private RegistrationDTO registration;

    @NotNull
    private CourseDTO course;

    @NotNull
    @Size(min=3, max=100)
    private String classroom;

}
