package com.example.colegio.services;


import com.example.colegio.dto.RegistroAlumnoDTO;
import com.example.colegio.entities.Alumno;
import com.example.colegio.entities.Usuario;
import com.example.colegio.enums.Rol;
import com.example.colegio.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * La anotación @Service le avisa al framework que la clase tiene un rol de servicio dentro del sistema[cite: 13].
 */
@Service
public class AlumnoService {

    /**
     * La anotación @Autowired implementa el patrón de Inyección de Dependencias (DI)[cite: 13].
     */
    @Autowired
    private AlumnoRepository alumnoRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * La anotación @Transactional se encarga de abrir la transacción por ti de forma invisible[cite: 13].
     * Si el método finaliza con éxito, Spring hace el commit oficializando los cambios. Si ocurre cualquier error o excepción, hace un rollback[cite: 13].
     */
    @Transactional
    public void registrarAlumno(RegistroAlumnoDTO dto) {
        Usuario u = new Usuario();
        u.setCorreo(dto.getCorreo());
        u.setClave(passwordEncoder.encode(dto.getClave()));
        u.setRol(Rol.ALUMNO);

        Alumno a = new Alumno();
        a.setNombre(dto.getNombre());
        a.setApellido(dto.getApellido());
        a.setDni(dto.getDni());
        a.setSexo(dto.getSexo());
        a.setFechaNacimiento(dto.getFechaNacimiento());
        a.setGrado(dto.getGrado());
        a.setLegajo(dto.getLegajo());

        // Asociamos el usuario al alumno
        a.setUsuario(u);

        // El método save() analiza si el objeto es nuevo o si ya existe previamente en la base de datos[cite: 9].
        // Si detecta que el registro es nuevo, ejecuta un INSERT INTO[cite: 9].
        alumnoRepo.save(a);
    }

    @Transactional
    public void modificarAlumno(Long id, RegistroAlumnoDTO dto) throws Exception {
        Alumno a = alumnoRepo.findById(id)
                .orElseThrow(() -> new Exception("Alumno no encontrado"));

        a.setNombre(dto.getNombre());
        a.setApellido(dto.getApellido());
        a.setDni(dto.getDni());
        a.setSexo(dto.getSexo());
        a.setFechaNacimiento(dto.getFechaNacimiento());
        a.setGrado(dto.getGrado());
        a.setLegajo(dto.getLegajo());

        // Si detecta que el registro ya existe, ejecuta un UPDATE para actualizarlo[cite: 9].
        alumnoRepo.save(a);
    }

    @Transactional
    public void eliminarAlumno(Long id) {
        // Al ejecutar este DELETE, la base de datos destruye al Alumno y,
        // gracias al CascadeType.ALL definido en la clase Persona, eliminará su Usuario automáticamente.
        alumnoRepo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Alumno> listarPorMateria(Long materiaId) {
        return alumnoRepo.findByMateriasCursadasId(materiaId);
    }
}
