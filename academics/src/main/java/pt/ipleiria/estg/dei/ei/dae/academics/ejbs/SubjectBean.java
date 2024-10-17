package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;

import java.util.List;

@Stateless
public class SubjectBean {
    @PersistenceContext
    private EntityManager em;

    @EJB
    private CourseBean cb;

    public List<Subject> findAll() {
        return em.createNamedQuery("getAllSubjects", Subject.class).getResultList();
    }

    public Subject find(long code){
        Subject s = em.find(Subject.class, code);
        if(s == null)
            throw new RuntimeException("Subject with code " + code + " not found");

        return s;
    }

    public void create(long code, String name, String schoolYear, int courseYear, long courseCode) {
        Course course = cb.find(courseCode);
        Subject s = new Subject(code, name, schoolYear, courseYear, course);
        course.addSubject(s);
        em.persist(s);
    }}
