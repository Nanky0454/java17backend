package org.proyectofinal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {
    private Integer idStudent;

    @NotNull
    @Size(min=3, max=50)
    private String nameStudent;

    @NotNull
    @Size(min=3, max=50)
    private String lastName;

    @NotNull
    @Size(min=8, max=8)
    private String dni;

    @NotNull
    @Max(value=100)
    @Min(value=1)
    private Integer age;
}
