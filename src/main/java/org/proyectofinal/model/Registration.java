package org.proyectofinal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idRegistration;

    @Column(nullable = false)
    private LocalDateTime datetime;

    @ManyToOne
    @JoinColumn(name="id_student", nullable = false, foreignKey = @ForeignKey(name="FK_REGISTRATION_STUDENT"))
    private Student student;

    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL)
    private List<RegistrationDetail> details;


}
