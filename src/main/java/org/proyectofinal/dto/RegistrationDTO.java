package org.proyectofinal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationDTO {
    private Integer idRegistration;

    @NotNull
    private StudentDTO student;

    @NotNull
    private LocalDateTime datetime;

    @NotNull
    private boolean enabled;

    @JsonManagedReference
    @NotNull
    private List<RegistrationDetailDTO> details;
}
