package org.proyectofinal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class RegistrationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idRegistrationDetail;

    @ManyToOne
    @JoinColumn(name="id_registration", nullable = false, foreignKey = @ForeignKey(name="FK_REGISTRATION_DETAIL_REGISTRATION"))
    private Registration registration;

    @ManyToOne
    @JoinColumn(name="id_course", nullable = false, foreignKey = @ForeignKey(name="FK_REGISTRATION_DETAIL_COURSE"))
    private Course course;

    @Column(nullable = false, length = 50)
    private String classroom;
}
