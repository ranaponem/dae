package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;

import java.util.List;

@Stateless
public class CourseBean {

    @PersistenceContext
    private EntityManager em;


    public List<Course> findAll() {
        return em.createNamedQuery("getAllCourses", Course.class).getResultList();
    }

    public Course find(long code){
        Course c = em.find(Course.class, code);
        if(c == null)
            throw new RuntimeException("Course with code " + code + " not found");

        return c;
    }

    public void create(long code, String name) {
        Course c = new Course(code, name);
        em.persist(c);
    }
}
