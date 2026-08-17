package app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.Alumno;
import logica.Carrera;
import logica.Controladora;
import logica.Materia;
import org.h2.tools.Server;
import persistence.ControladoraPersistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class pruebaJPA {
    public static void main(String[] args) {
        Server h2Server = null;
        try {
            //iniciar servidor web de la consola de h2
            h2Server = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
        } catch (Exception e) {
            System.out.println("Hubo un error al arrancar la base de datos:");
        }

        Controladora control = new Controladora();

        ///creamos la lista de materias
        List<Materia> listaMaterias = new ArrayList<Materia>();

        ///creamos carrera
//        Carrera carre1 = new Carrera("Mecatronica");
//        control.crearCarrera(carre1);

        Carrera carre2 = new Carrera("LCC", listaMaterias);
        control.crearCarrera(carre2);

        ///creamos materias
        Materia mat1 = new Materia("Programacion I", "Semestral", carre2);
        Materia mat2 = new Materia("Ingeniería de Software", "Anual", carre2);
        Materia mat3 = new Materia("Programacion II", "Semestral", carre2);

        control.crearMateria(mat1);
        control.crearMateria(mat2);
        control.crearMateria(mat3);

        ///Las ponemos en la lista atributo de carrera
        listaMaterias.add(mat1);
        listaMaterias.add(mat2);
        listaMaterias.add(mat3);

        ///ahora si podemos guardar las materias en la base de datos
        carre2.setMaterias(listaMaterias);
        control.editarCarrera(carre2);

        ///creamos alumnos
//        Alumno alu = new Alumno("Aiti", "Ferreyra", new Date(), carre1);
//        control.crearAlmuno(alu);

        Alumno alu2 = new Alumno("Leandro", "Spadaro", new Date(), carre2);
        control.crearAlmuno(alu2);

        //control.eliminarAlumno(alu.getId());

        //alu.setApellido("Denz");
        //control.editarAlmuno(alu);

        //Alumno buscado = control.traerAlumno(alu.getId());
        //System.out.println(buscado.getNombre() + " " + buscado.getApellido());


        ArrayList<Alumno> alumnos = control.traerAlumnos();
        for (Alumno al : alumnos) {
            System.out.println(al.toString());
        }
    }
}
