package com.example.colegio.services;

import com.example.colegio.entities.Alumno;
import com.example.colegio.entities.Materia;
import com.example.colegio.repositories.AlumnoRepository;
import com.example.colegio.repositories.MateriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MateriaService {
    @Autowired
    private MateriaRepository materiaRepo;

    @Autowired
    private AlumnoRepository alumnoRepo;

    @Transactional
    public List<Materia> listarTodas() {
        return materiaRepo.findAll();
    }

    @Transactional
    public Materia buscarPorId(Long id) throws Exception {
        return materiaRepo.findById(id).orElseThrow(() -> new Exception("Materia no encontrada"));
    }

    @Transactional
    public void inscribirAlumno(Long materiaId, String correoAlumno) throws Exception {
        Materia materia = materiaRepo.findById(materiaId)
                .orElseThrow(() -> new Exception("Materia no encontrada"));
        Alumno alumno = alumnoRepo.findByUsuarioCorreo(correoAlumno)
                .orElseThrow(() -> new Exception("Alumno no encontrado"));

        if (materia.getAlumnos() == null) {
            materia.setAlumnos(new ArrayList<>());
        }

        boolean yaInscripto = materia.getAlumnos().stream()
                .anyMatch(a -> a.getId().equals(alumno.getId()));

        if (yaInscripto) {
            throw new Exception("Ya estás inscripto en esta materia");
        }

        // Materia es el lado propietario del @JoinTable("materia_alumno"),
        // así que agregando acá y guardando la materia, Hibernate inserta la fila en la tabla intermedia.
        materia.getAlumnos().add(alumno);
        materiaRepo.save(materia);
    }

    @Transactional
    public boolean estaInscripto(Long materiaId, String correoAlumno) throws Exception {
        Materia materia = materiaRepo.findById(materiaId)
                .orElseThrow(() -> new Exception("Materia no encontrada"));
        Alumno alumno = alumnoRepo.findByUsuarioCorreo(correoAlumno)
                .orElseThrow(() -> new Exception("Alumno no encontrado"));

        return materia.getAlumnos() != null && materia.getAlumnos().stream()
                .anyMatch(a -> a.getId().equals(alumno.getId()));
    }
}