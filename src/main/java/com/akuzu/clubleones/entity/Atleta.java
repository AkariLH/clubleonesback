package com.akuzu.clubleones.entity;

import com.akuzu.clubleones.util.Sexo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Atleta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Atleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAtleta;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(length = 50)
    private String apellidoPaterno;

    @Column(length = 50)
    private String apellidoMaterno;

    @Column(nullable = false)
    private Date fechaDeNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexo sexo;

    @Column(nullable = false, unique = true, length = 50)
    private String correo;

    @Column(nullable = true, length = 255)
    private String contrasena;

    @Column(precision = 5, scale = 2)
    private BigDecimal peso;

    @Column(precision = 4, scale = 2)
    private BigDecimal estatura;

    @ManyToOne
    @JoinColumn(name = "id_equipo", nullable = true)
    private Equipo equipo;

}

