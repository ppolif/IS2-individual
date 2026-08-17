package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.Alumno;

import java.io.Serializable;
import java.util.List;


public class AlumnoJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    // constructor, si accedemos a la clase de persistencia es porque necesitamos em
    public AlumnoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("guiaJpaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // alta
    public void create(Alumno alumno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(alumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // busqueda
    public Alumno findAlumno(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }

    // listar, para esto necesitamos JPAQL
    public List<Alumno> findAlumnoEntities() {
        EntityManager em = getEntityManager();
        try {
            //
            return em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();
        } finally {
            em.close();
        }
    }

    // modificacion
    public void edit(Alumno alumno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            alumno = em.merge(alumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // baja
    public void destroy(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno alumno = em.find(Alumno.class, id);
            em.remove(alumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
