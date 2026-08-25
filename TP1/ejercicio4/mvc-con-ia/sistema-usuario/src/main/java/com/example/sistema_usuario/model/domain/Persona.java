package com.example.sistema_usuario.model.domain;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


/**
 * CAPA DE MODELO (Entity)
 * @Entity: Indica a JPA que esta clase es una entidad que se mapeará a una tabla en MySQL.
 * @Inheritance: Estrategia JOINED crea una tabla para Persona y otra para Usuario unidas por ID,
 * respetando la herencia orientada a objetos del diagrama.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {
    // @Id define la clave primaria de la tabla.
    // @GeneratedValue indica que MySQL autoincrementará este valor.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column define propiedades de la columna en la BD.
    @Column(nullable = false)
    private String nombre; //

    @Column(nullable = false)
    private String apellido; //

    @Column(nullable = false, unique = true)
    private int dni; // Documento, definido como int en el diagrama

    // @DateTimeFormat es una anotación de Spring para formatear fechas que vienen de formularios HTML.
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNac; // Representa el tipo Date del diagrama

    // Constructores, Getters y Setters (Omitidos por brevedad, pero obligatorios en el código real)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public int getDni() { return dni; }
    public void setDni(int dni) { this.dni = dni; }
    public LocalDate getFechaNac() { return fechaNac; }
    public void setFechaNac(LocalDate fechaNac) { this.fechaNac = fechaNac; }
}
