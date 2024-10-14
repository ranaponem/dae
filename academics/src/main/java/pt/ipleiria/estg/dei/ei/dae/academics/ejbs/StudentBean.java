package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import java.util.List;

@Stateless
public class StudentBean {
    @PersistenceContext
    private EntityManager em;

    public List<Student> findAll() {
        return em.createNamedQuery("getAllStudents", Student.class).getResultList();
    }

    public void create(String username, String password, String name, String email) {
        Student s = new Student(username, password, name, email);
        em.persist(s);
    }
}
