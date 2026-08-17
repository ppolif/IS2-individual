package logica;

import persistence.ControladoraPersistencia;

import java.util.ArrayList;

public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void crearAlmuno(Alumno alu) {
        controlPersis.crearAlumno(alu);
    }

    public void eliminarAlumno(int id) {
        controlPersis.eliminarAlumno(id);
    }

    public void editarAlmuno(Alumno alu) {
        controlPersis.editarAlumno(alu);
    }

    public Alumno traerAlumno(int id) {
        return controlPersis.traerAlumno(id);
    }

    public ArrayList<Alumno> traerAlumnos() {
        return controlPersis.traerListaAlumnos();
    }

    //carrera
    public void crearCarrera(Carrera carrera) {
        controlPersis.crearCarrera(carrera);
    }

    public void eliminarCarrera(int id) {
        controlPersis.eliminarCarrera(id);
    }

    public void editarCarrera(Carrera carrera) {
        controlPersis.editarCarrera(carrera);
    }

    public Carrera traerCarrera(int id) {
        return controlPersis.traerCarrera(id);
    }

    public ArrayList<Carrera> traerCarreras() {
        return controlPersis.traerListaCarreras();
    }

    //materia
    public void crearMateria(Materia materia) {
        controlPersis.crearMateria(materia);
    }

    public void eliminarMateria(int id) {
        controlPersis.eliminarMateria(id);
    }

    public void editarMateria(Materia materia) {
        controlPersis.editarMateria(materia);
    }

    public Materia traerMateria(int id) {
        return controlPersis.traerMateria(id);
    }

    public ArrayList<Materia> traerMaterias() {
        return controlPersis.traerListaMaterias();
    }
}
