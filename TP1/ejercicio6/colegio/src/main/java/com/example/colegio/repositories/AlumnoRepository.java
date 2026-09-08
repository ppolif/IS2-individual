package com.example.colegio.repositories;

import com.example.colegio.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    // Spring Data JPA traduce automáticamente el nombre de este método a un JOIN SQL
    // para traerte todos los alumnos asociados al ID de una materia específica.
    List<Alumno> findByMateriasCursadasId(Long materiaId);

    // Para ubicar al Alumno a partir del usuario autenticado (Authentication#getName() = correo)
    Optional<Alumno> findByUsuarioCorreo(String correo);
}
