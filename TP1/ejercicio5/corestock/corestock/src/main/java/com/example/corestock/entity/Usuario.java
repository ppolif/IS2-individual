package com.example.corestock.entity;

import com.example.corestock.enumeraciones.RolUsuario;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Entidad Usuario para Spring Security.
 * Implementa UserDetails para que Spring Security pueda usarla
 * directamente durante el proceso de autenticación (login).
 *
 * @Entity: Marca la clase como entidad JPA.
 * @Table: Define el nombre de la tabla.
 */
@Entity
@Table(name = "usuarios")
@Data
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private RolUsuario rol;

    // ---- Métodos requeridos por la interfaz UserDetails ----

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Spring Security espera authorities con el prefijo "ROLE_"
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}