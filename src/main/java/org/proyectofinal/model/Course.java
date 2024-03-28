package org.proyectofinal.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idCourse;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 6)
    private String acronym;

    @Column(nullable = false)
    private boolean enabled;
}
