package persistence;

import logica.Alumno;
import logica.Carrera;
import logica.Materia;

import java.util.ArrayList;
import java.util.List;

public class ControladoraPersistencia {

    AlumnoJpaController aluJpa = new AlumnoJpaController();

    CarreraJpaControladora carreJpa = new CarreraJpaControladora();

    MateriaJpaControladora materiaJpa = new MateriaJpaControladora();

    public void crearAlumno(Alumno alu) {
        aluJpa.create(alu);
    }

    public void eliminarAlumno(int id) {
        try {
            aluJpa.destroy(id);
        } catch (Exception e) {
            System.out.println("Alumno no encontrado");
        }
    }

    public void editarAlumno(Alumno alu) {
        try {
            aluJpa.edit(alu);
        } catch (Exception e) {
            System.out.println("Alumno no editado");
        }
    }

    public Alumno traerAlumno(int id) {
        return aluJpa.findAlumno(id);
    }

    public ArrayList<Alumno> traerListaAlumnos() {
        List<Alumno> lista = aluJpa.findAlumnoEntities();
        ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>(lista);
        return listaAlumnos;
    }

    //carrera
    public void crearCarrera(Carrera carrera) {
        carreJpa.create(carrera);
    }

    public void eliminarCarrera(int id) {
        try {
            carreJpa.destroy(id);
        } catch (Exception e) {
            System.out.println("Carrera no encontrada");
        }
    }

    public void editarCarrera(Carrera carrera) {
        try {
            carreJpa.edit(carrera);
        } catch (Exception e) {
            System.out.println("Carrera no editada");
        }
    }

    public Carrera traerCarrera(int id) {
        return carreJpa.findCarrera(id);
    }

    public ArrayList<Carrera> traerListaCarreras() {
        List<Carrera> lista = carreJpa.findCarreraEntities();
        ArrayList<Carrera> listaCarreras = new ArrayList<Carrera>(lista);
        return listaCarreras;
    }

    //materia
    public void crearMateria(Materia materia) {
        materiaJpa.create(materia);
    }

    public void eliminarMateria(int id) {
        try {
            materiaJpa.destroy(id);
        } catch (Exception e) {
            System.out.println("Materia no encontrada");
        }
    }

    public void editarMateria(Materia materia) {
        try {
            materiaJpa.edit(materia);
        } catch (Exception e) {
            System.out.println("Materia no editada");
        }
    }

    public Materia traerMateria(int id) {
        return materiaJpa.findMateria(id);
    }

    public ArrayList<Materia> traerListaMaterias() {
        List<Materia> lista = materiaJpa.findMateriaEntities();
        ArrayList<Materia> listaMaterias = new ArrayList<Materia>(lista);
        return listaMaterias;
    }
}
