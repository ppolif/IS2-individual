package com.example.colegio.dto;

import com.example.colegio.enums.Grado;
import com.example.colegio.enums.Sexo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class RegistroAlumnoDTO {
    private String nombre;
    private String apellido;
    private int dni;
    private Sexo sexo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    private Grado grado;
    private int legajo;

    // Credenciales de acceso
    private String correo;
    private String clave;
}
