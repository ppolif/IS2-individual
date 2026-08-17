package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import logica.Materia;

import java.util.List;

public class MateriaJpaControladora {

    private EntityManagerFactory emf = null;

    public MateriaJpaControladora() {
        this.emf = Persistence.createEntityManagerFactory("guiaJpaPU");
    }


    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public void create(Materia materia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(materia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public Materia findMateria(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Materia.class, id);
        } finally {
            em.close();
        }
    }


    public List<Materia> findMateriaEntities() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT m FROM Materia m");
            return q.getResultList();
        } finally {
            em.close();
        }
    }


    public void edit(Materia materia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            materia = em.merge(materia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public void destroy(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materia materia = em.getReference(Materia.class, id);
            em.remove(materia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
