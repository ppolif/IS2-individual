package com.example.colegio.dto;

import com.example.colegio.enums.Sexo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class RegistroProfesorDTO {
    private String nombre;
    private String apellido;
    private int dni;
    private Sexo sexo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    private String titulo;
    private String correo;
    private String clave;
}