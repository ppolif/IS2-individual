package com.example.sistema_usuario.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * CAPA DE MODELO (Entity)
 * Al extender de Persona, JPA unirá sus atributos.
 * @Entity: Mapea la clase a la tabla "usuario" en la base de datos MySQL.
 */

@Entity
public class Usuario extends Persona{

    // El correo es el usuario del sistema, por ende debe ser único.
    @Column(nullable = false, unique = true)
    private String correo; //

    @Column(nullable = false)
    private String clave; //

    // Bandera para saber si el usuario está bloqueado o activo.
    @Column(nullable = false)
    private boolean activo = true; //

    // Contador de intentos de login fallidos.
    @Column(nullable = false)
    private int intentosPorDia = 0; //

    // Getters y Setters
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public int getIntentosPorDia() { return intentosPorDia; }
    public void setIntentosPorDia(int intentosPorDia) { this.intentosPorDia = intentosPorDia; }
}
