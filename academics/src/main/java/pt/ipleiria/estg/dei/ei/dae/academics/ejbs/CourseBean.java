package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityExistsException;

import java.util.List;

@Stateless
public class CourseBean {

    @PersistenceContext
    private EntityManager em;


    public List<Course> findAll() {
        return em.createNamedQuery("getAllCourses", Course.class).getResultList();
    }

    public Course find(long code){
        return em.find(Course.class, code);
    }

    public boolean exists(long code) {
        Query query = em.createQuery(
                "SELECT COUNT(c.code) FROM Course c WHERE c.code = :code",
                Long.class
        );
        query.setParameter("code", code);
        return (Long)query.getSingleResult() > 0L;
    }
    public void create(long code, String name) throws MyEntityExistsException, MyConstraintViolationException {
        if(exists(code))
            throw new MyEntityExistsException("Course with code " + code + " already exists");
        try{
            Course c = new Course(code, name);
            em.persist(c);
        }
        catch(ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }
    }
}
